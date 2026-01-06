package com.spss.glowlytic.service;


public interface EmailService {
    void sendOtpEmail(String to, String otp);
}