package com.dimas.stockdataaggregator.model.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExceptionMessage {
    final String message;
    final Long timestamp = System.currentTimeMillis();
}
