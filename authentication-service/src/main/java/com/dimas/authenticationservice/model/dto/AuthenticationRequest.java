package com.dimas.authenticationservice.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "User authentication model")
public class AuthenticationRequest {
    @Schema(description = "Username", example = "dimas")
    private String username;
    @Schema(description = "Username", example = "dimas")
    private String password;
    @Schema(description = "Username", example = "gohasoxx@gmail.com")
    private String email;
}
