package com.dimas.authenticationservice.exception;

import com.dimas.authenticationservice.constant.Message;

public class GeneratingTokenException extends RuntimeException {
    public GeneratingTokenException(Message message) {
        super(message.getMessage());
    }
}
