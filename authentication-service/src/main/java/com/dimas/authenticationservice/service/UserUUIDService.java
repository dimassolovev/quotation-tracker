package com.dimas.authenticationservice.service;

import com.dimas.authenticationservice.model.entity.UserCredentials;
import com.dimas.authenticationservice.model.entity.UserUUID;

public interface UserUUIDService {
    UserUUID findByUserCredentials(UserCredentials userCredentials);
}
