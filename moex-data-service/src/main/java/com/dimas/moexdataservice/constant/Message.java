package com.dimas.moexdataservice.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Message {
    CURRENCY_NOT_FOUND("Currency not found");

    private final String message;
}
