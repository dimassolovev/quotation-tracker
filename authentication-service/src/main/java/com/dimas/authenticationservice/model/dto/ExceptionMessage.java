package com.dimas.authenticationservice.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ExceptionMessage {
    private final String message;
    private final Long timestamp = System.currentTimeMillis();
}
