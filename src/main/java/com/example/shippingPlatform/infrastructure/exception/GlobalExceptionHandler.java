package com.example.shippingPlatform.infrastructure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        Map<String, Object> problemDetails = new HashMap<>();
        problemDetails.put("type", "https://example.com/problems/illegal-state");
        problemDetails.put("title", "Invalid argument exception");
        problemDetails.put("status", HttpStatus.BAD_REQUEST.value());
        problemDetails.put("detail", ex.getMessage());
        problemDetails.put("instance", request.getDescription(false));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetails);
    }
}
