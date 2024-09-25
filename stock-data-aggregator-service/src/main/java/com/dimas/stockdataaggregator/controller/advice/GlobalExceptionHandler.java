package com.dimas.stockdataaggregator.controller.advice;

import com.dimas.stockdataaggregator.exception.ParseObjectException;
import com.dimas.stockdataaggregator.model.dto.ExceptionMessage;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ParseObjectException.class)
    public ResponseEntity<ExceptionMessage> handleGeneratingTokenException(ParseObjectException exception) {
        return new ResponseEntity<>(new ExceptionMessage(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
