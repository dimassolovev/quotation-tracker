package com.dimas.authenticationservice.exceptions;

import com.dimas.authenticationservice.constants.Messages;
import lombok.Getter;

@Getter
public class EntryException extends RuntimeException {
    private final String message;

    public EntryException(Messages message) {
        this.message = message.getMessage();
    }
}
