package com.adminease.backend.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ErrorResponse(
        String message,
        String details,
        HttpStatus status,
        LocalDateTime timestamp) {
}
