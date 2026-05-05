package com.simone.lms.services;

import com.simone.lms.exception.UserException;
import com.simone.lms.payload.dto.UserDTO;
import com.simone.lms.payload.response.AuthResponse;
import jakarta.mail.MessagingException;

public interface AuthService {

    AuthResponse login(String userName, String password)  throws UserException;
    AuthResponse signUp(UserDTO userDTO) throws UserException;

    void createPasswordResetToken(String email) throws UserException, MessagingException;
    void resetPassword(String token, String password) throws Exception;

}
