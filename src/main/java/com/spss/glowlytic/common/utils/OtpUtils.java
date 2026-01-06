package com.spss.glowlytic.common.utils;

import java.security.SecureRandom;

public class OtpUtils {
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generateOtp() {
        int otpValue = 100000 + RANDOM.nextInt(900000);
        return String.valueOf(otpValue);
    }

    private OtpUtils() {}
}
