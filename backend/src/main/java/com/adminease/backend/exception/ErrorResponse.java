package com.adminease.backend.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class ErrorResponse {
    private final String message;
    private final String details;
    private final HttpStatus status;
    private final LocalDateTime timestamp;
}
