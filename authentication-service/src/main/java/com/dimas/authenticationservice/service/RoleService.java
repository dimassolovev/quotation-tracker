package com.dimas.authenticationservice.service;

import com.dimas.authenticationservice.model.entity.Role;

public interface RoleService {
    Role findByName(String name);
}
