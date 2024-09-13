package com.dimas.gatewayservice.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;


@Component
public class JwtUtil {
    private static final String secret = "^&*$QHO89yhosdf9*()Uhosdf(**#$JKLhsudfh87&@#&*@#$hjksdfbimd52525252";

    public void validateToken(final String token) {
        SecretKey secretKey = Keys.hmacShaKeyFor(Base64.getEncoder().encodeToString(secret.getBytes()).getBytes(StandardCharsets.UTF_8));

        Jws<Claims> claims = Jwts
                .parser().verifyWith(secretKey).build()
                .parseSignedClaims(token);
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
