package com.dimas.authenticationservice.service;

import com.dimas.authenticationservice.model.entity.UserCredentials;
import com.dimas.authenticationservice.model.entity.UserUUID;
import com.dimas.authenticationservice.repository.UserUUIDRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserUUIDServiceImplementation implements UserUUIDService {
    private final UserUUIDRepository userUUIDRepository;

    @Override
    public UserUUID findByUserCredentials(UserCredentials userCredentials) {
        return this.userUUIDRepository.findByUserCredentials(userCredentials);
    }
}
