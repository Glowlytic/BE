package com.spss.glowlytic.service;

public interface JwtService {
    String generateToken(String username, boolean isRefresh);
    long getRemainingTime(String token);
}
