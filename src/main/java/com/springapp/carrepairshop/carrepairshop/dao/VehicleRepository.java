package com.springapp.carrepairshop.carrepairshop.dao;

import com.springapp.carrepairshop.carrepairshop.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle,Integer>
{
}
