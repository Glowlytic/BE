package com.spss.glowlytic.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    USER_EXISTED(1000, "User is already existed!", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(1001, "User not found!", HttpStatus.NOT_FOUND),
    USER_BANNED(1002, "Account is banned!", HttpStatus.FORBIDDEN),
    INVALID_CREDENTIALS(1003, "Invalid username/email or password. Please try again!", HttpStatus.UNAUTHORIZED),
    EMAIL_EXISTED(1004, "Email is already existed!", HttpStatus.BAD_REQUEST);

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;
}