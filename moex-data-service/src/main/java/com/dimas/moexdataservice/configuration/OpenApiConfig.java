package com.dimas.moexdataservice.configuration;

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
                description = "OpenApi documentation for MOEX data service.",
                title = "OpenApi specification - dimas",
                version = "1.0"
        ),
        servers = {
                @Server(
                        description = "LOCAL ENV",
                        url = "http://localhost:56781"
                )
        }
)
public class OpenApiConfig {
}
