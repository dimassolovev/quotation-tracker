package com.dimas.authenticationservice.exception;

import com.dimas.authenticationservice.constant.Messages;

import lombok.*;

@Getter
public class GeneratingTokenException extends RuntimeException{
    private final String message;

    public GeneratingTokenException(Messages message) {
        this.message = message.getMessage();
    }
}
