package com.spss.glowlytic.dto.request.auth;

import lombok.Data;

@Data
public class LoginRequest {
    private String identifier;
    private String password;
}
