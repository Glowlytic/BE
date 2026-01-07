package com.spss.glowlytic.controller;

import com.spss.glowlytic.dto.request.auth.*;
import com.spss.glowlytic.dto.response.auth.LoginResponse;
import com.spss.glowlytic.dto.response.general.ApiResponse;
import com.spss.glowlytic.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @Operation(summary = "This endpoint for user to register")
    public ApiResponse<String> register(@RequestBody CreateUserRequest request) {
        authService.register(request);
        return ApiResponse.<String>builder()
                .message("User registered successfully")
                .build();
    }

    @PostMapping("/login")
    @Operation(summary = "This endpoint for user to login")
    public ApiResponse<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse result = authService.login(request);
        return ApiResponse.<LoginResponse>builder()
                .result(result)
                .message("Login successful")
                .build();
    }

    @PostMapping("/logout")
    @Operation(summary = "This endpoint for user to logout using redis")
    public ApiResponse<String> logout(
            @RequestHeader("Authorization") String accessToken,
            @RequestBody LogoutRequest request
    ) {
        authService.logout(request, accessToken);
        return ApiResponse.<String>builder()
                .message("Logout successful")
                .build();
    }


    @PostMapping("/forgot-password")
    @Operation(summary = "This endpoint for user to forgot password")
    public ApiResponse<String> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        authService.forgotPassword(request.getEmail());
        return ApiResponse.<String>builder()
                .message("OTP has been sent to your email")
                .build();
    }

    @PostMapping("/reset-password")
    @Operation(summary = "This endpoint for user to reset password")
    public ApiResponse<String> resetPassword(@RequestBody ResetPasswordRequest request) {
        authService.resetPassword(request);
        return ApiResponse.<String>builder()
                .message("Password has been reset successfully")
                .build();
    }




}