package com.dimas.authenticationservice.mapper;

import com.dimas.authenticationservice.model.dto.AuthenticationRequest;
import com.dimas.authenticationservice.model.entity.UserCredentials;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserCredentialsMapper extends Mappable<UserCredentials, AuthenticationRequest> { }
