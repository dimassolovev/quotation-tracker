package com.dimas.moexdataservice.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Message {
    INCORRECT_DATE_FORMAT("Wrong date format");

    private final String message;
}
