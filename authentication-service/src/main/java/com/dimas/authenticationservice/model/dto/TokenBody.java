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
    @Schema(description = "jwt token", example = "eyJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImdvaGFzb3h4QGdtYWlsLmNvbSIsInN1YiI6ImRpbWFzIiwiaWF0IjoxNzI5NDI0MTY4LCJleHAiOjE3Mjk1MTA1Njh9.88fqbpvhPlP5GIihSxqS-XOrCyfQeb7q0hgGot38WeQ")
    private String token;
}
