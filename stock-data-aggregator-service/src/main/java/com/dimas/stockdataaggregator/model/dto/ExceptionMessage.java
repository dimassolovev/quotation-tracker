package com.dimas.stockdataaggregator.model.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ExceptionMessage {
    private final String message;
    private final Long timestamp = System.currentTimeMillis();
}
