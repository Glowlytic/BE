package com.spss.glowlytic.service;

import com.spss.glowlytic.dto.request.auth.CreateUserRequest;
import com.spss.glowlytic.dto.request.auth.LoginRequest;
import com.spss.glowlytic.dto.request.auth.LogoutRequest;
import com.spss.glowlytic.dto.request.auth.ResetPasswordRequest;
import com.spss.glowlytic.dto.response.auth.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest request);
    void register(CreateUserRequest request);
    void logout(LogoutRequest request, String accessToken);
    void forgotPassword(String email);
    void resetPassword(ResetPasswordRequest request);
}
