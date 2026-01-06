package com.spss.glowlytic.exception;

import com.spss.glowlytic.dto.response.general.ErrorResponse;
import com.spss.glowlytic.enums.ErrorCode;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity<ErrorResponse> buildResponse(HttpStatus httpStatus, int businessCode, String message, WebRequest request) {
        return new ResponseEntity<>(
                new ErrorResponse(
                        LocalDateTime.now(),
                        businessCode,
                        httpStatus.getReasonPhrase(),
                        message,
                        request.getDescription(false).replace("uri=", "")
                ),
                httpStatus
        );
    }

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ErrorResponse> handleAppException(AppException ex, WebRequest request) {
        ErrorCode errorCode = ex.getErrorCode();
        return buildResponse(
                (HttpStatus) errorCode.getStatusCode(),
                errorCode.getCode(),
                errorCode.getMessage(),
                request
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex, WebRequest request) {
        String enumKey = ex.getBindingResult().getFieldError() != null
                ? ex.getBindingResult().getFieldError().getDefaultMessage()
                : "INVALID_KEY";

        ErrorCode errorCode;
        String finalMessage;

        try {
            errorCode = ErrorCode.valueOf(enumKey);
            finalMessage = errorCode.getMessage();
        } catch (IllegalArgumentException e) {
            errorCode = ErrorCode.INVALID_KEY;
            finalMessage = enumKey;
        }
        return buildResponse(
                (HttpStatus) errorCode.getStatusCode(),
                errorCode.getCode(),
                finalMessage,
                request
        );
    }

    @ExceptionHandler({AccessDeniedException.class, AuthorizationDeniedException.class})
    public ResponseEntity<ErrorResponse> handleAccessDenied(Exception ex, WebRequest request) {
        return buildResponse(HttpStatus.FORBIDDEN, ErrorCode.UNAUTHORIZED.getCode(), "Access denied: You do not have permission.", request);
    }

    @ExceptionHandler({JwtException.class, AuthenticationServiceException.class})
    public ResponseEntity<ErrorResponse> handleAuthExceptions(Exception ex, WebRequest request) {
        return buildResponse(HttpStatus.UNAUTHORIZED, ErrorCode.UNAUTHENTICATED.getCode(), "Authentication failed: " + ex.getMessage(), request);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
        String detailedMessages = ex.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(", "));
        return buildResponse(HttpStatus.BAD_REQUEST, ErrorCode.INVALID_KEY.getCode(), detailedMessages, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex, WebRequest request) {
        log.error("Uncategorized error", ex);
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.UNCATEGORIZED_EXCEPTION.getCode(), "An unexpected internal error occurred.", request);
    }
}