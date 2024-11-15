package com.dimas.authenticationservice.service.implementation;

import com.dimas.authenticationservice.constant.Message;
import com.dimas.authenticationservice.exception.RoleNotFoundException;
import com.dimas.authenticationservice.model.entity.Role;
import com.dimas.authenticationservice.repository.RoleRepository;
import com.dimas.authenticationservice.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RoleServiceImplementation implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role findByName(String name) {
        return this.roleRepository.findByName(name).orElseThrow(() -> new RoleNotFoundException(Message.ROLE_NOT_FOUND));
    }
}
