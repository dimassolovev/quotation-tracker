package com.dimas.authenticationservice.service.implementation;

import com.dimas.authenticationservice.constant.Message;
import com.dimas.authenticationservice.exception.UserNotFoundException;
import com.dimas.authenticationservice.repository.UserCredentialsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {
    private final UserCredentialsRepository userCredentialsRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        var userCredentials = this.userCredentialsRepository
                .findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(Message.USER_NOT_FOUND));

        return new User(
                userCredentials.getUsername(),
                userCredentials.getPassword(),
                userCredentials.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).toList()
        );
    }
}
