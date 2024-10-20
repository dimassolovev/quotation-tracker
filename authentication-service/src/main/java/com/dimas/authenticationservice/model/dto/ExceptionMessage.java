package com.dimas.authenticationservice.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Schema(description = "Error model")
public class ExceptionMessage {
    @Schema(description = "Error message", example = "User credentials already exist. Change username or email.")
    private final String message;
    @Schema(description = "Timestamp in seconds", example = "1729423650")
    private final Long timestamp = System.currentTimeMillis();
}
