package com.dimas.quotationdataaggregatorservice.exception;

import com.dimas.quotationdataaggregatorservice.constant.Message;
import lombok.Getter;

@Getter
public class ParseObjectException extends RuntimeException{
    private final String message;

    public ParseObjectException(Message message) {
        this.message = message.getMessage();
    }
}
