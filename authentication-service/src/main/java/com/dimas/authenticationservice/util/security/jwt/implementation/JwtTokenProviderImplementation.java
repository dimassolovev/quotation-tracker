package com.dimas.authenticationservice.util.security.jwt.implementation;

import com.dimas.authenticationservice.constant.JwtProperties;
import com.dimas.authenticationservice.util.security.jwt.JwtTokenProvider;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtTokenProviderImplementation implements JwtTokenProvider {
    private final JwtProperties jwtProperties;
    private SecretKey secretKey;

    @PostConstruct
    public void init() {
        var secret = Base64.getEncoder().encodeToString(jwtProperties.getSecretKey().getBytes());
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String createToken(Map<String, Object> claims, String username) {
        var now = new Date();
        var validity = new Date(now.getTime() + this.jwtProperties.getTimeMillis());
        return Jwts.builder()
                .claims(claims)
                .subject(username)
                .issuedAt(now)
                .expiration(validity)
                .signWith(this.secretKey, Jwts.SIG.HS256)
                .compact();

    }

    @Override
    public boolean validateToken(String token) {
        try {
            var claims = Jwts
                    .parser().verifyWith(this.secretKey).build()
                    .parseSignedClaims(token);
            log.info("expiration date: {}", claims.getPayload().getExpiration());

            return true;

        } catch (JwtException | IllegalArgumentException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        }

        return false;
    }
}
