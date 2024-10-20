package com.dimas.authenticationservice.service;

import com.dimas.authenticationservice.model.dto.AuthenticationRequest;

public interface AuthenticationService {
    void register(AuthenticationRequest authenticationRequest);

    String login(AuthenticationRequest authenticationRequest);

    boolean validateToken(String token);
}
