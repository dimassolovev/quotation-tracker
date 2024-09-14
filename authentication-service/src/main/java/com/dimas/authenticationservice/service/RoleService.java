package com.dimas.authenticationservice.service;

import com.dimas.authenticationservice.constant.Messages;
import com.dimas.authenticationservice.exception.RoleNotFoundException;
import com.dimas.authenticationservice.model.entity.Role;
import com.dimas.authenticationservice.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
public class RoleService {
    private final RoleRepository roleRepository;

    public Role findByName(String name) {
        return roleRepository.findByName(name).orElseThrow(() -> new RoleNotFoundException(Messages.ROLE_NOT_FOUND));
    }
}
