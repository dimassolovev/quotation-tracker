package com.dimas.authenticationservice.exceptions;

import com.dimas.authenticationservice.constants.Messages;
import lombok.Getter;

@Getter
public class RoleNotFoundException extends RuntimeException {
    private final String message;

    public RoleNotFoundException(Messages message) {
        this.message = message.getMessage();
    }
}
