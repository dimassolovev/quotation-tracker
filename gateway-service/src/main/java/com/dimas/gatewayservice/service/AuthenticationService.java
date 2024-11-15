package com.dimas.gatewayservice.service;

import com.dimas.gatewayservice.model.TokenBody;
import reactor.core.publisher.Mono;

public interface AuthenticationService {
    Mono<Boolean> validateToken(TokenBody tokenBody);
}
