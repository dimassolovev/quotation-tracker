package com.dimas.authenticationservice.exception;

import com.dimas.authenticationservice.constant.Message;

public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException(Message message) {
        super(message.getMessage());
    }
}
