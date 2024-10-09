package com.dimas.gatewayservice.filter;

import com.dimas.gatewayservice.constant.UriProperties;

import lombok.RequiredArgsConstructor;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class RouteValidator {
    private final UriProperties uriProperties;

    public Predicate<ServerHttpRequest> isSecured() {
        return request -> Stream.of(uriProperties.getRegisterEndpoint(), uriProperties.getTokenEndpoint())
                .noneMatch(uri -> request.getURI().getPath().contains(uri));
    }
}
