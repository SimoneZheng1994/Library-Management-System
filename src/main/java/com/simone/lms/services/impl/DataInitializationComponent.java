package com.simone.lms.services.impl;


import com.simone.lms.domain.UserRole;
import com.simone.lms.model.User;
import com.simone.lms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializationComponent implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run (String... args) {
        initializeAdminUser();
    }

    private void initializeAdminUser() {
        String adminEmail = "simone.zheng.07@gmail.com";
        String adminPassword = "1234567890";

        if (userRepository.findByEmail(adminEmail) == null) {

            User user = User.builder()
                    .userName("Admin")
                    .password(passwordEncoder.encode(adminPassword))
                    .email(adminEmail)
                    .fullName("Simone Zheng")
                    .role(UserRole.ROLE_ADMIN)
                    .build();

            User admin = userRepository.save(user);
        }
    }

}
