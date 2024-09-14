package com.dimas.authenticationservice.util.security.jwt;

import com.dimas.authenticationservice.constant.JwtProperties;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class JwtTokenProvider {
    private final JwtProperties jwtProperties;
    private SecretKey secretKey;

    @PostConstruct
    public void init() {
        String secret = Base64.getEncoder().encodeToString(jwtProperties.getSecretKey().getBytes());
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String createToken(Map<String, Object> claims, String username) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + jwtProperties.getTimeMillis());

        return Jwts.builder()
                .claims(claims)
                .subject(username)
                .issuedAt(now)
                .expiration(validity)
                .signWith(secretKey, Jwts.SIG.HS256)
                .compact();

    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts
                    .parser().verifyWith(this.secretKey).build()
                    .parseSignedClaims(token);
            log.info("expiration date: {}", claims.getPayload().getExpiration());

            return true;

        }

        catch (JwtException | IllegalArgumentException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        }

        return false;
    }
}
