package com.springapp.carrepairshop.carrepairshop.dao;

import com.springapp.carrepairshop.carrepairshop.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer>
{
    Role findRoleByName(String name);
}
