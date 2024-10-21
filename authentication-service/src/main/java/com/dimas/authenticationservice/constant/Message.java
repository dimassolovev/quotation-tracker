package com.dimas.authenticationservice.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Message {
    USER_NOT_FOUND("User not found"),
    ROLE_NOT_FOUND("Role not found"),
    USER_CREDENTIALS_EXIST("User credentials already exist. Change username or email.");

    private final String message;
}
