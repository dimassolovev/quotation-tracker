package com.dimas.authenticationservice.repository;

import com.dimas.authenticationservice.model.entity.UserCredentials;
import com.dimas.authenticationservice.model.entity.UserUUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserUUIDRepository extends JpaRepository<UserUUID, Integer> {
    UserUUID findByUserCredentials(UserCredentials userCredentials);
}
