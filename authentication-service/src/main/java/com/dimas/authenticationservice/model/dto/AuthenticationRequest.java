package com.dimas.authenticationservice.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "User authentication model")
public class AuthenticationRequest {
    @Schema(description = "Username", example = "dimas")
    @NotBlank(message = "Username must be filled")
    private String username;
    @Schema(description = "Username", example = "dimas")
    @NotBlank(message = "Password must be filled")
    private String password;
    @Schema(description = "Username", example = "gohasoxx@gmail.com")
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;
}
