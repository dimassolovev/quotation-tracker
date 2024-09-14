package com.dimas.gatewayservice.constant;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "authentication")
public class UriProperties {
    private String baseUrl;
    private String validateTokenEndpoint;
    private String tokenEndpoint;
    private String registerEndpoint;
}
