package com.dimas.authenticationservice.services;

import com.dimas.authenticationservice.constants.Messages;
import com.dimas.authenticationservice.exceptions.EntryException;
import com.dimas.authenticationservice.exceptions.GeneratingTokenException;
import com.dimas.authenticationservice.exceptions.RoleNotFoundException;
import com.dimas.authenticationservice.mappers.UserCredentialsMapper;
import com.dimas.authenticationservice.models.dto.AuthenticationRequest;
import com.dimas.authenticationservice.models.entities.Role;
import com.dimas.authenticationservice.models.entities.UserCredentials;
import com.dimas.authenticationservice.repositories.UserCredentialsRepository;
import com.dimas.authenticationservice.utils.security.jwt.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Transactional(readOnly = true)
public class AuthenticationService {
    private final UserCredentialsRepository userCredentialsRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final RoleService roleService;

    @Transactional
    public void saveUser(AuthenticationRequest authenticationRequest) {
        UserCredentials userCredentials = UserCredentialsMapper.INSTANCE.authenticationRequestAuthenticationToUserCredentials(authenticationRequest);
        Role role = roleService.findByName("ROLE_USER");

        userCredentials.setPassword(
                passwordEncoder.encode(userCredentials.getPassword())
        );
        userCredentials.setRoles(new ArrayList<>());
        userCredentials.getRoles().add(role);

        try {
            userCredentialsRepository.save(userCredentials);
        }
        catch (Exception e) {
            log.error(e.getMessage());
            throw new EntryException(Messages.USER_CREDENTIALS_EXIST);
        }
    }

    public String generateToken(AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getUsername(), authenticationRequest.getPassword()
                    )
            );

            return jwtTokenProvider.createToken(Map.of(), authenticationRequest.getUsername());
        }

        catch (InternalAuthenticationServiceException exception) {
            throw new GeneratingTokenException(Messages.USER_NOT_FOUND);
        }

        catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            throw new RuntimeException();
        }
    }

    public boolean validateToken(String token) {
        return jwtTokenProvider.validateToken(token);
    }
}
