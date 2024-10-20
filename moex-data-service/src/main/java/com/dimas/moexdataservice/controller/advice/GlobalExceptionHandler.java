package com.dimas.moexdataservice.controller.advice;

import com.dimas.moexdataservice.exception.IncorrectDateFormat;
import com.dimas.moexdataservice.model.dto.other.ExceptionMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IncorrectDateFormat.class)
    public ResponseEntity<ExceptionMessage> handleGeneratingTokenException(IncorrectDateFormat exception) {
        return new ResponseEntity<>(new ExceptionMessage(exception.getMessage()), HttpStatus.METHOD_NOT_ALLOWED);
    }
}
