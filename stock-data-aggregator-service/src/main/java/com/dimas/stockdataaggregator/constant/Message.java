package com.dimas.stockdataaggregator.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Message {
    PARSE_OBJECT_EXCEPTION("Error parsing data from moscow exchange"),
    UNKNOWN_ERROR("Unknown error");

    private final String message;
}
