package com.dimas.quotationdataaggregatorservice.exception;

import lombok.Getter;
import com.dimas.quotationdataaggregatorservice.constant.Message;

/**
 * The error that is thrown after incorrect processing of the response from moex.
 */
@Getter
public class ParseObjectException extends RuntimeException{
    private final String message;

    public ParseObjectException(Message message) {
        this.message = message.getMessage();
    }
}
