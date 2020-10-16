package com.javamentor.springbootcrud.repository;

import com.javamentor.springbootcrud.entity.Role;

public interface RoleRepository{
    Role getById (Long id);
}
