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
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        if (status != null) {
            try {
                httpStatus = HttpStatus.valueOf(Integer.parseInt(status.toString()));
            } catch (Exception ex) {
                log.error("Error parsing status code: {}", status, ex);
            }
        }

        String path = (String) request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);
        if (path == null) {
            path = request.getRequestURI();
        }

        String generalMessage = httpStatus.getReasonPhrase();
        String detailsMessage = switch (httpStatus) {
            case NOT_FOUND -> "The requested resource at path [" + path + "] was not found.";
            case FORBIDDEN -> "You don't have permission to access this resource.";
            case BAD_REQUEST -> "The request could not be understood due to malformed syntax.";
            default -> "An unexpected error occurred.";
        };

        Object ex = request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
        if (httpStatus.is5xxServerError() && ex instanceof Exception exception) {
            log.error("Unhandled server exception at path {}:", path, exception);
        }

        ErrorResponse errorResponse = new ErrorResponse(
                generalMessage,
                detailsMessage,
                httpStatus,
                LocalDateTime.now()
        );

        return new ResponseEntity<>(errorResponse, httpStatus);
    }
}
