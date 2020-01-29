package com.springapp.carrepairshop.carrepairshop.validation;

import com.springapp.carrepairshop.carrepairshop.entity.Vehicle;

public class VehicleValidation
{
    public boolean validateVehicle(Vehicle vehicle)
    {
        if (vehicle.getBrand().isEmpty() || vehicle.getNumbers().isEmpty() || vehicle.getStatus().isEmpty())
            return false;
        return true;
    }
}