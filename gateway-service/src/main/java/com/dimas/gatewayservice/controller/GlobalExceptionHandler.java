package com.dimas.gatewayservice.controller;

import com.dimas.gatewayservice.exception.FilterException;

import com.dimas.gatewayservice.model.ExceptionMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import reactor.core.publisher.Mono;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FilterException.class)
    public Mono<ResponseEntity<ExceptionMessage>> handleException(Exception exception) {
        return Mono.just(ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionMessage(exception.getMessage())));
    }

}
