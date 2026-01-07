package com.spss.glowlytic.dto.request.auth;

import lombok.Data;

@Data
public class ForgotPasswordRequest {
    private String email;
}