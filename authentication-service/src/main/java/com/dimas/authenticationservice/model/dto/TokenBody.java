package com.dimas.authenticationservice.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Token model")
public class TokenBody {
    @Schema(description = "jwt token", example = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwiZW1haWwiOiJnb2hhc294eEBnbWFpbC5jb20iLCJzdWIiOiJkaW1hcyIsImlhdCI6MTcyOTQyNjE1NiwiZXhwIjoxNzI5NTEyNTU2fQ.DZH_iPQ9-5tBb8oXU1GmUi-tvEbp0-Iun9Zyqc4o9P0")
    private String token;
}
