package com.springapp.carrepairshop.carrepairshop.rest.handler.vehicle;

public class VehicleNotFoundException extends RuntimeException
{
    public VehicleNotFoundException(String message)
    {
        super(message);
    }
}
