package com.dimas.authenticationservice.constant;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "services")
public class ServiceProperties {
    private Integer discoveryServicePort;
    private Integer apiGatewayPort;
    private Integer configServicePort;
}
