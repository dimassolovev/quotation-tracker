package com.dimas.authenticationservice.exception;

import com.dimas.authenticationservice.constant.Messages;

import lombok.Getter;

@Getter
public class EntryException extends RuntimeException {
    private final String message;

    public EntryException(Messages message) {
        this.message = message.getMessage();
    }
}
