package com.dimas.authenticationservice.mapper;

import com.dimas.authenticationservice.model.dto.AuthenticationRequest;
import com.dimas.authenticationservice.model.entity.UserCredentials;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserCredentialsMapper extends Mappable<UserCredentials, AuthenticationRequest> { }
