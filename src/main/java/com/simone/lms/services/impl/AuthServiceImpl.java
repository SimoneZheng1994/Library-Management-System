package com.simone.lms.services.impl;

import com.simone.lms.domain.UserRole;
import com.simone.lms.exception.UserException;
import com.simone.lms.model.User;
import com.simone.lms.payload.dto.UserDTO;
import com.simone.lms.payload.response.AuthResponse;
import com.simone.lms.repository.UserRepository;
import com.simone.lms.services.AuthService;
import io.jsonwebtoken.security.Password;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse login(String userName, String password) {
        return null;
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

        return null;
    }

    @Override
    public void createPasswordResetToken(String email) {

    }

    @Override
    public void resetPassword(String token, String password) {

    }
}
