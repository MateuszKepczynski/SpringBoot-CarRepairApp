package com.springapp.carrepairshop.carrepairshop.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "owner")
public class Owner extends Person
{
    @JsonBackReference
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,
            CascadeType.REFRESH})
    @JoinColumn(name = "id_employee")
    private User user;

    @JsonManagedReference
    @OneToMany(mappedBy="owner")
    private List<Vehicle> vehicles;


    public Owner()
    {
    }

    public List<Vehicle> getVehicles()
    {
        return vehicles;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public void setVehicles(List<Vehicle> vehicles)
    {
        this.vehicles = vehicles;
    }


    public void add(Vehicle vehicle)
    {
        if(vehicles == null)
        {
            vehicles = new ArrayList<>();
        }
        vehicles.add(vehicle);
        vehicle.setOwner(this);
    }
}

