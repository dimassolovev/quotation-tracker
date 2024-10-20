package com.dimas.authenticationservice.service;

import com.dimas.authenticationservice.constant.Message;
import com.dimas.authenticationservice.exception.EntryException;
import com.dimas.authenticationservice.exception.GeneratingTokenException;
import com.dimas.authenticationservice.mapper.UserCredentialsMapper;
import com.dimas.authenticationservice.model.dto.AuthenticationRequest;
import com.dimas.authenticationservice.model.entity.UserCredentials;
import com.dimas.authenticationservice.model.entity.UserUUID;
import com.dimas.authenticationservice.repository.UserCredentialsRepository;
import com.dimas.authenticationservice.repository.UserUUIDRepository;
import com.dimas.authenticationservice.util.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthenticationServiceImplementation implements AuthenticationService {
    private final UserCredentialsMapper userCredentialsMapper;
    private final UserCredentialsRepository userCredentialsRepository;
    private final UserUUIDRepository userUUIDRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final RoleService roleService;

    @Override
    @Transactional
    public void register(AuthenticationRequest authenticationRequest) throws EntryException {
        UserCredentials userCredentials = this.userCredentialsMapper.toEntity(authenticationRequest);

        userCredentials.setPassword(
                this.passwordEncoder.encode(userCredentials.getPassword())
        );
        userCredentials.setRoles(new ArrayList<>());
        userCredentials.getRoles().add(this.roleService.findByName("ROLE_USER"));

        try {
            userCredentials = this.userCredentialsRepository.save(userCredentials);

            UserUUID userUUID = UserUUID.builder()
                    .userUuid(UUID.randomUUID())
                    .userCredentials(userCredentials)
                    .build();

            this.userUUIDRepository.save(userUUID);

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new EntryException(Message.USER_CREDENTIALS_EXIST);
        }
    }

    @Override
    public String login(AuthenticationRequest authenticationRequest) throws GeneratingTokenException {
        try {
            Authentication authentication = this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getUsername(), authenticationRequest.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return jwtTokenProvider.createToken(Map.of(
                            "email", authenticationRequest.getEmail(),
                            "roles", SecurityContextHolder
                                    .getContext()
                                    .getAuthentication()
                                    .getAuthorities()
                                    .stream()
                                    .map(GrantedAuthority::getAuthority)
                                    .toList(),
                            "uuid", this.userCredentialsRepository
                                    .findByUsername(authenticationRequest.getUsername())
                                    .orElseThrow(() -> new GeneratingTokenException(Message.USER_NOT_FOUND))
                                    .getUserUUID().getUserUuid()

                    ),
                    authenticationRequest.getUsername()
            );
        } catch (InternalAuthenticationServiceException exception) {
            throw new GeneratingTokenException(Message.USER_NOT_FOUND);
        }
    }

    @Override
    public boolean validateToken(String token) {
        return this.jwtTokenProvider.validateToken(token);
    }
}
