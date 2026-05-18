package com.simone.lms.payload.dto;

import com.simone.lms.domain.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;

    private String email;

    private String phoneNumber;

    private String password;

    private String fullName;

    private UserRole role;

    private String userName;

    private LocalDateTime lastLogin;
}