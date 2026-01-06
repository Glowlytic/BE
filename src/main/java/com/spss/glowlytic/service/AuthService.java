package com.spss.glowlytic.service;

import com.spss.glowlytic.dto.request.CreateUserRequest;
import com.spss.glowlytic.dto.request.LoginRequest;
import com.spss.glowlytic.dto.request.LogoutRequest;
import com.spss.glowlytic.dto.request.ResetPasswordRequest;
import com.spss.glowlytic.dto.response.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest request);
    void register(CreateUserRequest request);
    void logout(LogoutRequest request, String accessToken);
    void forgotPassword(String email);
    void resetPassword(ResetPasswordRequest request);
}
