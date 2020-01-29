package com.springapp.carrepairshop.carrepairshop.validation;

import com.springapp.carrepairshop.carrepairshop.entity.User;

public class UserValidation
{
    public boolean validateUser(User user)
    {
        if (user.getFirstName().isEmpty() || user.getLastName().isEmpty() || user.getAddress().isEmpty() || user.getCity().isEmpty()
                || user.getPhoneNumber().isEmpty() || user.getUsername().isEmpty() || user.getPassword().isEmpty())
            return false;

        return true;
    }
}
