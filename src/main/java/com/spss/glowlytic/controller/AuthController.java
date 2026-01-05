package com.spss.glowlytic.controller;

import com.spss.glowlytic.dto.request.CreateUserRequest;
import com.spss.glowlytic.dto.request.LoginRequest;
import com.spss.glowlytic.dto.request.LogoutRequest;
import com.spss.glowlytic.dto.response.LoginResponse;
import com.spss.glowlytic.dto.response.general.ApiResponse;
import com.spss.glowlytic.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ApiResponse<String> register(@RequestBody CreateUserRequest request) {
        authService.register(request);
        return ApiResponse.<String>builder()
                .message("User registered successfully")
                .build();
    }

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse result = authService.login(request);
        return ApiResponse.<LoginResponse>builder()
                .result(result)
                .message("Login successful")
                .build();
    }

    @PostMapping("/logout")
    public ApiResponse<String> logout(
            @RequestHeader("Authorization") String accessToken,
            @RequestBody LogoutRequest request
    ) {
        authService.logout(request, accessToken);
        return ApiResponse.<String>builder()
                .message("Logout successful")
                .build();
    }
}