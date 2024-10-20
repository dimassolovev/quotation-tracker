package com.dimas.authenticationservice.service;

import com.dimas.authenticationservice.constant.Message;
import com.dimas.authenticationservice.exception.RoleNotFoundException;
import com.dimas.authenticationservice.model.entity.Role;
import com.dimas.authenticationservice.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role findByName(String name) {
        return roleRepository.findByName(name).orElseThrow(() -> new RoleNotFoundException(Message.ROLE_NOT_FOUND));
    }
}
