package com.springapp.carrepairshop.carrepairshop.validation;

import com.springapp.carrepairshop.carrepairshop.entity.Owner;

public class OwnerValidation
{
    public boolean validOwner(Owner owner)
    {
        if(owner.getFirstName().isEmpty() || owner.getLastName().isEmpty() || owner.getAddress().isEmpty() ||  owner.getPhoneNumber().isEmpty()
                || owner.getCity().isEmpty())
            return false;

        return true;
    }
}
