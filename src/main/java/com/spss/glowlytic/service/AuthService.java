package com.spss.glowlytic.service;

import com.spss.glowlytic.dto.request.CreateUserRequest;
import com.spss.glowlytic.dto.request.LoginRequest;
import com.spss.glowlytic.dto.response.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest request);
    void register(CreateUserRequest request);
}
