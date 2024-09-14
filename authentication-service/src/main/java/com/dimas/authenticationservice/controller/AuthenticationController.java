package com.dimas.authenticationservice.controller;

import com.dimas.authenticationservice.model.dto.AuthenticationRequest;
import com.dimas.authenticationservice.model.dto.TokenBody;
import com.dimas.authenticationservice.service.AuthenticationService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authentication")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/token")
    public ResponseEntity<TokenBody> token(@RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(
                new TokenBody(
                        authenticationService.generateToken(authenticationRequest)
                )
        );
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody AuthenticationRequest authenticationRequest) {
        authenticationService.saveUser(authenticationRequest);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/validate-token")
    public ResponseEntity<Boolean> validateToken(@RequestBody TokenBody tokenBody) {
        return ResponseEntity.ok(
                authenticationService.validateToken(tokenBody.getToken())
        );
    }
}
