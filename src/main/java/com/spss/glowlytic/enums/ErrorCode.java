package com.spss.glowlytic.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    USER_EXISTED(1000, "User already exists!", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(1001, "User not found!", HttpStatus.NOT_FOUND),
    USER_BANNED(1002, "Account is banned!", HttpStatus.FORBIDDEN),
    INVALID_CREDENTIALS(1003, "Invalid username/email or password.", HttpStatus.UNAUTHORIZED),
    EMAIL_EXISTED(1004, "Email already exists!", HttpStatus.BAD_REQUEST),
    INVALID_KEY(1005, "Validation error", HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(1006, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1007, "You do not have permission", HttpStatus.FORBIDDEN),
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),

    EMAIL_REQUIRED(1008, "Email is required", HttpStatus.BAD_REQUEST),
    INVALID_EMAIL(1009, "Invalid email format", HttpStatus.BAD_REQUEST),
    USERNAME_REQUIRED(1012, "Username is required", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID_SIZE(1013, "Username must be between 3 and 50 characters", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(1011, "Username must contain only letters and numbers", HttpStatus.BAD_REQUEST),
    PASSWORD_REQUIRED(1014, "Password is required", HttpStatus.BAD_REQUEST),
    PASSWORD_TOO_SHORT(1010, "Password must be at least 5 characters", HttpStatus.BAD_REQUEST),
    FIRST_NAME_REQUIRED(1015, "First name is required", HttpStatus.BAD_REQUEST),
    INVALID_FIRST_NAME(1016, "First Name must contain only letters and numbers", HttpStatus.BAD_REQUEST),
    INVALID_MIDDLE_NAME(1017, "Middle Name must contain only letters and numbers", HttpStatus.BAD_REQUEST),
    LAST_NAME_REQUIRED(1018, "Last name is required", HttpStatus.BAD_REQUEST),
    INVALID_LAST_NAME(1019, "Last Name must contain only letters and numbers", HttpStatus.BAD_REQUEST),
    GENDER_REQUIRED(1020, "Gender is required", HttpStatus.BAD_REQUEST),
    ROLE_NOT_FOUND(1021, "Role is not found in db", HttpStatus.NOT_FOUND),
    INVALID_OTP(1022, "Invalid otp. Please try again", HttpStatus.BAD_REQUEST);

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }
    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;
}