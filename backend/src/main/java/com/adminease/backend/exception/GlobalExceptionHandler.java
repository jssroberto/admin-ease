package com.adminease.backend.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;
import java.util.List;
// Assuming ErrorResponse and ApiRequestException are in the same package or imported

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Keep your existing handler for validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();

        // Consider passing the list directly if you change ErrorResponse.details to List<String> or Object
        // ErrorResponse errorResponse = new ErrorResponse("Validation failed", errors, HttpStatus.BAD_REQUEST, LocalDateTime.now());

        ErrorResponse errorResponse = new ErrorResponse(
                "Validation failed",
                errors.toString(), // Current implementation
                HttpStatus.BAD_REQUEST,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // *** ADD THIS HANDLER ***
    @ExceptionHandler(ApiRequestException.class)
    public ResponseEntity<ErrorResponse> handleApiRequestException(ApiRequestException ex) {
        // You might want to customize the HttpStatus based on the specific context
        // where ApiRequestException was thrown, or create more specific exceptions.
        // For now, let's assume it's generally a BAD_REQUEST (400).
        HttpStatus status = HttpStatus.BAD_REQUEST;

        ErrorResponse errorResponse = new ErrorResponse(
                "Application Request Error", // Generic title
                ex.getMessage(),             // Use the exception's message as details
                status,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, status);
    }

    // *** ADD HANDLER FOR 404 (Option A - using NoHandlerFoundException) ***
    // Requires properties: spring.mvc.throw-exception-if-no-handler-found=true AND spring.web.resources.add-mappings=false
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_FOUND.getReasonPhrase(), // "Not Found"
                "The requested endpoint [" + request.getRequestURI() + "] was not found.", // More specific message
                HttpStatus.NOT_FOUND,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }


    // *** OPTIONAL: Add a fallback handler for any other unexpected exceptions ***
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        // Log the exception ex here for debugging purposes!
        ErrorResponse errorResponse = new ErrorResponse(
                "Internal Server Error",
                "An unexpected error occurred. Please try again later.", // User-friendly message
                HttpStatus.INTERNAL_SERVER_ERROR,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
