package com.dimas.authenticationservice.exception;

import com.dimas.authenticationservice.constant.Message;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Message message) {
        super(message.getMessage());
    }
}
