package com.dimas.authenticationservice.services;

import com.dimas.authenticationservice.constants.Messages;
import com.dimas.authenticationservice.exceptions.RoleNotFoundException;
import com.dimas.authenticationservice.models.entities.Role;
import com.dimas.authenticationservice.repositories.RoleRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class RoleService {
    private final RoleRepository roleRepository;

    public Role findByName(String name) {
        return roleRepository.findByName(name).orElseThrow(() -> new RoleNotFoundException(Messages.ROLE_NOT_FOUND));
    }
}
