package com.dimas.moexdataservice.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Message {
    CURRENCY_NOT_FOUND("Currency not found"),
    INCORRECT_DATE_FORMAT("Wrong date format");

    private final String message;
}
