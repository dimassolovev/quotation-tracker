package com.dimas.authenticationservice.exception;

import com.dimas.authenticationservice.constant.Messages;

import lombok.Getter;

@Getter
public class RoleNotFoundException extends RuntimeException {
    private final String message;

    public RoleNotFoundException(Messages message) {
        this.message = message.getMessage();
    }
}
