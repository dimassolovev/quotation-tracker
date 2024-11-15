package com.dimas.quotationdataaggregatorservice.model.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum StatusType {
    REQUESTED("REQUESTED"),
    FAILED("FAILED"),
    COMPLETED("COMPLETED");

    private final String message;
}
