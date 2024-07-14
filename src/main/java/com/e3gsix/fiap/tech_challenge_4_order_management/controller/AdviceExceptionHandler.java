package com.e3gsix.fiap.tech_challenge_4_order_management.controller;

import com.e3gsix.fiap.tech_challenge_4_order_management.exceptions.NotFoundException;
import com.e3gsix.fiap.tech_challenge_4_order_management.exceptions.StandardError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.Instant;

@ControllerAdvice
public class AdviceExceptionHandler {
    @ExceptionHandler({NotFoundException.class, NoResourceFoundException.class})
    public ResponseEntity<StandardError> handleNotFoundException(NotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        final StandardError err = new StandardError(
                Instant.now(),
                status.value(),
                status.name(),
                e.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler({UnsupportedOperationException.class})
    public ResponseEntity<StandardError> handleUnsupportedOperationException(
            UnsupportedOperationException e,
            HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        final StandardError err = new StandardError(
                Instant.now(),
                status.value(),
                status.name(),
                e.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(status).body(err);
    }
}
