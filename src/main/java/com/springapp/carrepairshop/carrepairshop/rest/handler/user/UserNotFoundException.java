package com.springapp.carrepairshop.carrepairshop.rest.handler.user;

public class UserNotFoundException extends RuntimeException
{
    public UserNotFoundException(String message)
    {
        super(message);
    }
}
