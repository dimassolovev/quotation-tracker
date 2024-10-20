package com.dimas.gatewayservice.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Messages {
    ROUTE_IS_NOT_SECURED("Route is not secured"),
    AUTHENTICATION_HEADER_FAILED("Missing Authorization header"),
    EMPTY_AUTHENTICATION_HEADER("Empty Authorization header"),
    INVALID_TOKEN_FORMAT("Invalid token format"),
    TOKEN_VALIDATION_FAILED("Token validation failed"),
    ;

    private final String message;
}
