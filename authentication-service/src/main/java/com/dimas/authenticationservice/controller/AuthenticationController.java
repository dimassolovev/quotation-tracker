package com.dimas.authenticationservice.controller;

import com.dimas.authenticationservice.exception.EntryException;
import com.dimas.authenticationservice.exception.GeneratingTokenException;
import com.dimas.authenticationservice.model.dto.AuthenticationRequest;
import com.dimas.authenticationservice.model.dto.ExceptionMessage;
import com.dimas.authenticationservice.model.dto.TokenBody;
import com.dimas.authenticationservice.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Authentication")
@RestController
@RequestMapping("${other.authentication.related_path}")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @Operation(
            description = "Endpoint for generating a token for the user",
            summary = "Returns json with token",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TokenBody.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Bad request",
                            responseCode = "400",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionMessage.class)
                            )
                    )
            }
    )
    @PostMapping("/token")
    public ResponseEntity<TokenBody> token(
            @Parameter(description = "Request with username, password and email")
            @RequestBody AuthenticationRequest authenticationRequest
    ) throws EntryException {

        return ResponseEntity.ok(
                new TokenBody(
                        authenticationService.generateToken(authenticationRequest)
                )
        );

    }

    @Operation(
            description = "Endpoint for for user registration",
            summary = "Returns nothing except http status code",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(schema = @Schema(hidden = true))
                    ),
                    @ApiResponse(
                            description = "Bad request",
                            responseCode = "400",
                            content = @Content(schema = @Schema(hidden = true))
                    )
            }
    )
    @PostMapping("/register")
    public ResponseEntity<Void> register(
            @Parameter(description = "Request with username, password and email")
            @RequestBody AuthenticationRequest authenticationRequest
    ) throws GeneratingTokenException {

        authenticationService.saveUser(authenticationRequest);

        return ResponseEntity.ok().build();
    }

    @Operation(
            description = "Endpoint for for verification the generated token",
            summary = "Returns nothing except http status code",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Boolean.class)
                            )
                    )
            }
    )
    @PostMapping("/validate-token")
    public ResponseEntity<Boolean> validateToken(
            @Parameter(description = "Request with generated token")
            @RequestBody TokenBody tokenBody
    ) {
        return ResponseEntity.ok(
                authenticationService.validateToken(tokenBody.getToken())
        );
    }
}
