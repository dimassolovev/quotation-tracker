package com.dimas.authenticationservice.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "dimas",
                        email = "gohasoxx@gmail.com",
                        url = "https://t.me/dimassolovev"
                ),
                description = "OpenApi documentation for jwt authentication service.",
                title = "OpenApi specification - dimas",
                version = "1.0"
        ),
        servers = {
                @Server(
                        description = "LOCAL ENV",
                        url = "http://localhost:56782"
                )
        }
)
public class OpenApiConfig {
}