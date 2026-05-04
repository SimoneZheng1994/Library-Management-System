package com.simone.lms.services;

import com.simone.lms.exception.UserException;
import com.simone.lms.payload.dto.UserDTO;
import com.simone.lms.payload.response.AuthResponse;

public interface AuthService {

    AuthResponse login(String userName, String password);
    AuthResponse signUp(UserDTO userDTO) throws UserException;

    void createPasswordResetToken(String email);
    void resetPassword(String token, String password);

}
