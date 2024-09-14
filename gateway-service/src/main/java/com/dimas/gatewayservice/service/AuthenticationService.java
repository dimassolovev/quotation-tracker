package com.dimas.gatewayservice.service;

import com.dimas.gatewayservice.constant.UriProperties;
import com.dimas.gatewayservice.model.TokenBody;

import jakarta.annotation.PostConstruct;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationService {
    private final UriProperties uriProperties;
    private WebClient webClient;

    @PostConstruct
    public void init() {
        webClient = WebClient.builder().baseUrl(uriProperties.getBaseUrl()).build();
    }

    public Mono<Boolean> validateToken(TokenBody tokenBody) {
        return webClient
                .post()
                .uri(uriProperties.getValidateTokenEndpoint())
                .bodyValue(tokenBody)
                .retrieve()
                .bodyToMono(Boolean.class);
    }
}
