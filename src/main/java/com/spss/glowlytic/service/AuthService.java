package com.spss.glowlytic.service;

import com.spss.glowlytic.dto.request.LoginRequest;
import com.spss.glowlytic.dto.response.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest request);
}
