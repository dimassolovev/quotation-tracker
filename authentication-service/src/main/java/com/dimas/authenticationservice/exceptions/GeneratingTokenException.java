package com.dimas.authenticationservice.exceptions;

import com.dimas.authenticationservice.constants.Messages;
import lombok.*;

@Getter
public class GeneratingTokenException extends RuntimeException{
    private final String message;

    public GeneratingTokenException(Messages message) {
        this.message = message.getMessage();
    }
}
