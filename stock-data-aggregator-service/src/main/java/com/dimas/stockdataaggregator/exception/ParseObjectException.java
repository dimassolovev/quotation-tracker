package com.dimas.stockdataaggregator.exception;

import lombok.Getter;

import com.dimas.stockdataaggregator.constant.Message;

@Getter
public class ParseObjectException extends RuntimeException{
    private final String message;

    public ParseObjectException(Message message) {
        this.message = message.getMessage();
    }
}
