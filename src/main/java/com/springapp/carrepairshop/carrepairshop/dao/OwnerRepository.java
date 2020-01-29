package com.springapp.carrepairshop.carrepairshop.dao;

import com.springapp.carrepairshop.carrepairshop.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OwnerRepository extends JpaRepository<Owner,Integer>
{
    public List<Owner> findOwnerByUserId(int id);
    public List<Owner> findOwnerByLastName(String lastName);
}
