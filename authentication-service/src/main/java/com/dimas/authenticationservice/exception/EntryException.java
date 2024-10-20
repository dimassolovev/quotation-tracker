package com.dimas.authenticationservice.exception;

import com.dimas.authenticationservice.constant.Message;

public class EntryException extends RuntimeException {
    public EntryException(Message message) {
        super(message.getMessage());
    }
}
