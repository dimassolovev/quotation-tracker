package com.dimas.authenticationservice.model.dto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExceptionMessage {
    private final String message;
    private final Long timestamp = System.currentTimeMillis();
}
