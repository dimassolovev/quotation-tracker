package com.dimas.gatewayservice.exception;

import com.dimas.gatewayservice.constant.Messages;
import lombok.Getter;

@Getter
public class FilterException extends RuntimeException {
    private final String message;

    public FilterException(Messages message) {
        this.message = message.getMessage();
    }
}
