package com.dimas.authenticationservice.controller.advice;

import com.dimas.authenticationservice.exception.EntryException;
import com.dimas.authenticationservice.exception.GeneratingTokenException;
import com.dimas.authenticationservice.model.dto.ExceptionMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GeneratingTokenException.class)
    public ResponseEntity<ExceptionMessage> handleGeneratingTokenException(GeneratingTokenException exception) {
        return new ResponseEntity<>(new ExceptionMessage(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntryException.class)
    public ResponseEntity<ExceptionMessage> handleEntryException(EntryException exception) {
        return new ResponseEntity<>(new ExceptionMessage(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
