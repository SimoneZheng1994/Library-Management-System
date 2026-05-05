package com.simone.lms.services.impl;

import com.simone.lms.configurations.JwtProvider;
import com.simone.lms.domain.UserRole;
import com.simone.lms.exception.UserException;
import com.simone.lms.mapper.UserMapper;
import com.simone.lms.model.PasswordResetToken;
import com.simone.lms.model.User;
import com.simone.lms.payload.dto.UserDTO;
import com.simone.lms.payload.response.AuthResponse;
import com.simone.lms.repository.PasswordResetTokenRepository;
import com.simone.lms.repository.UserRepository;
import com.simone.lms.services.AuthService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.UUID;

import static com.simone.lms.mapper.UserMapper.toDTO;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final CustomUserServiceImpl customUserServiceImpl;
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final EmailServiceImpl emailServiceImpl;

    @Override
    public AuthResponse login(String userName, String password) throws UserException {

        Authentication authentication = authenticate(userName, password);

        SecurityContextHolder.getContext().setAuthentication(authentication);
//        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//        String role = authorities.iterator().next().getAuthority();
        String token = jwtProvider.generateToken(authentication);

        User user = userRepository.findByEmail(userName);

        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);

        AuthResponse response = new AuthResponse();
        response.setTitle("Login success");
        response.setMessage("Welcome back " + userName);
        response.setJwt(token);
        response.setUserDTO(UserMapper.toDTO(user));

        return response;
    }

    private Authentication authenticate(String userName, String password) throws UserException {

        UserDetails userDetails = customUserServiceImpl.loadUserByUsername(userName);

        if(userDetails == null) {
            throw new UserException("User not found with email - " + password);
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new UserException("Password does not match");
        }

        return new UsernamePasswordAuthenticationToken(userName,
                null, userDetails.getAuthorities());

    }

    @Override
    public AuthResponse signUp(UserDTO userDTO) throws UserException {

        User user = userRepository.findByEmail(userDTO.getEmail());

        if (user == null) {
            throw new UserException("E-mail is already registered");
        }

        User createdUser = new User();
        createdUser.setEmail(userDTO.getEmail());
        createdUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        createdUser.setPhoneNumber(userDTO.getPhoneNumber());
        createdUser.setFullName(userDTO.getFullName());
        createdUser.setLastLogin(LocalDateTime.now());
        createdUser.setRole(UserRole.ROLE_USER);

        User savedUser = userRepository.save(createdUser);

        Authentication auth = new UsernamePasswordAuthenticationToken(
                savedUser.getEmail(), savedUser.getPassword());
        SecurityContextHolder.getContext().setAuthentication(auth);


        String jwt = jwtProvider.generateToken(auth);

        AuthResponse response = new AuthResponse();
        response.setJwt(jwt);
        response.setTitle("Welcome " + createdUser.getFullName());
        response.setTitle("Registration successful!");
        response.setUserDTO(toDTO(savedUser));

        return response;
    }

    @Transactional
    public void createPasswordResetToken(String email) throws UserException, MessagingException {


        String frontendUrl = "";
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UserException("User not found with given email");
        }

        String token = UUID.randomUUID().toString();

        PasswordResetToken resetToken = PasswordResetToken.builder()
                .token(token)
                .user(user)
                .expiryDate(LocalDateTime.now().plusMinutes(5))
                .build();

        passwordResetTokenRepository.save(resetToken);
        String resetLink = frontendUrl + token;
        String subject = "Password reset request";
        String body = "You have requested to reset your password. Use this link, it will be valid for 5 minutes.";

        emailServiceImpl.sendEmail(user.getEmail(), subject, body);

    }

    @Transactional
    public void resetPassword(String token, String password) throws Exception {
        PasswordResetToken resetToken = passwordResetTokenRepository.findByToken(token)
                .orElseThrow(
                        () -> new Exception("Token not valid!")
                );

        if (resetToken.isExpired()) {
            passwordResetTokenRepository.delete(resetToken);
            throw new Exception("Token expired");
        }
    }
}
