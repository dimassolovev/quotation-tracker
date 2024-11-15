package com.dimas.authenticationservice.util.security.jwt;

import java.util.Map;

public interface JwtTokenProvider {
    String createToken(Map<String, Object> claims, String username);

    boolean validateToken(String token);
}
