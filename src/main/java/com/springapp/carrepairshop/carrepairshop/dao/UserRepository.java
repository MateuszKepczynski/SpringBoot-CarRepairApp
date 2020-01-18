package com.springapp.carrepairshop.carrepairshop.dao;

import com.springapp.carrepairshop.carrepairshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer>
{
    public List<User> findUserByLastName(String lastName);
}
