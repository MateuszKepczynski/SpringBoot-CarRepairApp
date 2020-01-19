package com.springapp.carrepairshop.carrepairshop.rest.handler.owner;

public class OwnerNotFoundException extends RuntimeException
{
    public OwnerNotFoundException(String message)
    {
        super(message);
    }
}
