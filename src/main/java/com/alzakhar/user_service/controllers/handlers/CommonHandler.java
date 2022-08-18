package com.alzakhar.user_service.controllers.handlers;

import com.alzakhar.user_service.exceptions.CommonException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CommonHandler {

    @ExceptionHandler(CommonException.class)
    public ResponseEntity<Object> handleCustomException(CommonException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(
            BindException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<Object> handleInvalidFormatExceptions(
            InvalidFormatException ex) {
        return new ResponseEntity<>("Invalid format of <" + ex.getValue() +
                ">, it is impossible to deserialize it to " + ex.getPathReference(), HttpStatus.BAD_REQUEST);
    }
}