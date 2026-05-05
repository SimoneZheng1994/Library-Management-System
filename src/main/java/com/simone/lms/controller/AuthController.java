package com.simone.lms.controller;

import com.simone.lms.exception.UserException;
import com.simone.lms.payload.dto.UserDTO;
import com.simone.lms.payload.request.ForgotPasswordRequest;
import com.simone.lms.payload.request.LoginRequest;
import com.simone.lms.payload.request.ResetPasswordRequest;
import com.simone.lms.payload.response.ApiResponse;
import com.simone.lms.payload.response.AuthResponse;
import com.simone.lms.services.AuthService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signupHandler (
            @RequestBody @Valid UserDTO userDTO
    ) throws UserException {
        AuthResponse authResponse = authService.signUp(userDTO);
        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginHandler (
            @RequestBody @Valid LoginRequest loginRequest
    ) throws UserException {
        AuthResponse authResponse = authService.login(loginRequest.getUserName(), loginRequest.getPassword());
        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<ApiResponse> forgotPassword (
            @RequestBody ForgotPasswordRequest request
    ) throws UserException, MessagingException {

        authService.createPasswordResetToken(request.getEmail());

        ApiResponse response = new ApiResponse(
                "A reset link was sent to your email.", true
        );

        return ResponseEntity.ok(response);

    }

    @PostMapping("/reset-password")
    public ResponseEntity<ApiResponse> resetPassword (
            @RequestBody ResetPasswordRequest request
    ) throws  Exception {

        authService.resetPassword(request.getToken(), request.getPassword());

        ApiResponse response = new ApiResponse(
                "Password reset successful.", true
        );

        return ResponseEntity.ok(response);

    }

}
