package com.dimas.gatewayservice.filter;

import com.dimas.gatewayservice.constant.Messages;
import com.dimas.gatewayservice.exception.FilterException;
import com.dimas.gatewayservice.model.TokenBody;
import com.dimas.gatewayservice.service.AuthenticationService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@Setter(onMethod = @__(@Autowired))
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
    private RouteValidator routeValidator;
    private AuthenticationService authenticationService;

    public AuthenticationFilter() {
        super(AuthenticationFilter.Config.class);
    }

    @Override
    public GatewayFilter apply(AuthenticationFilter.Config config) {
        return (exchange, chain) -> Mono.just(routeValidator.isSecured().test(exchange.getRequest()))
                .filter(isSecured -> isSecured)
                .switchIfEmpty(Mono.error(new FilterException(Messages.ROUTE_IS_NOT_SECURED)))
                .then(Mono.defer(() -> {
                    var hasAuthorizationHeader = exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION);
                    if (!hasAuthorizationHeader) {
                        return Mono.error(new FilterException(Messages.AUTHENTICATION_HEADER_FAILED));
                    }

                    var authorizationHeaders = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION);
                    if (authorizationHeaders == null || authorizationHeaders.isEmpty()) {
                        return Mono.error(new FilterException(Messages.EMPTY_AUTHENTICATION_HEADER));
                    }

                    var token = authorizationHeaders.get(0);
                    if (token == null || !token.startsWith("Bearer ")) {
                        return Mono.error(new FilterException(Messages.INVALID_TOKEN_FORMAT));
                    }

                    var actualToken = token.substring(7);
                    return authenticationService.validateToken(new TokenBody(actualToken))
                            .flatMap(isValid -> {
                                if (!isValid) {
                                    return Mono.error(new FilterException(Messages.TOKEN_VALIDATION_FAILED));
                                }
                                return Mono.empty();
                            });
                }))
                .then(chain.filter(exchange));
    }

    public static class Config {
    }
}
