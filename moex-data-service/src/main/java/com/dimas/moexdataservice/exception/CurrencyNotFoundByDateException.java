package com.dimas.moexdataservice.exception;

import com.dimas.moexdataservice.constant.Message;

public class CurrencyNotFoundByDateException extends RuntimeException {
    public CurrencyNotFoundByDateException(Message message) {
        super(message.getMessage());
    }
}
