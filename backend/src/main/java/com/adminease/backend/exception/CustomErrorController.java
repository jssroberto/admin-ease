package com.adminease.backend.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Slf4j  
@RestController
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public ResponseEntity<ErrorResponse> handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR; // Default
        if (status == null) {
            try {
                httpStatus = HttpStatus.valueOf(Integer.parseInt(status.toString()));
            } catch (Exception ex) {
                log.error("Error parsing status code: {}", status, ex);
            }
        }

        String path = (String) request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);
        if (path == null) {
            path = request.getRequestURI(); // Fallback to current request URI if error URI isn't set
        }


        String detailsMessage = "An unexpected error occurred.";
        String generalMessage = httpStatus.getReasonPhrase(); // e.g., "Not Found", "Internal Server Error"

        if (httpStatus == HttpStatus.NOT_FOUND) {
            // Customize the message specifically for 404
            detailsMessage = "The requested resource or endpoint at path [" + path + "] was not found.";
            generalMessage = "Resource Not Found"; // More user-friendly general message
        } else {
            // You could try to get a more specific message for other errors if available
            Object ex = request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
            if (ex instanceof Exception exception) {
                // Don't expose ex.getMessage() directly unless you trust its content!
                // Maybe log it and keep a generic message for the client.
                detailsMessage = "An internal error occurred while processing the request."; // Safer generic message
            }
        }


        // *** Construct YOUR Custom DTO ***
        ErrorResponse errorResponse = new ErrorResponse(
                generalMessage,  // Use the determined general message
                detailsMessage,   // Use the determined details message
                httpStatus,       // Pass the HttpStatus object (or httpStatus.value())
                LocalDateTime.now()
        );

        return new ResponseEntity<>(errorResponse, httpStatus);
    }

    // getErrorPath() is deprecated and usually not needed.
}