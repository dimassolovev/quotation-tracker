package com.dimas.moexdataservice.exception;

import com.dimas.moexdataservice.constant.Message;

public class IncorrectDateFormat extends RuntimeException {
    public IncorrectDateFormat(Message message) {
        super(message.getMessage());
    }
}
