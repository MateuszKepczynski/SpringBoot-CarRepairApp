package com.springapp.carrepairshop.carrepairshop.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "vehicle")
public class Vehicle extends BaseEntity
{
    @Column(name = "brand")
    @NotNull
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z]).{2,}$", message = "Wrong brand name")
    private String brand;

    @Column(name = "numbers")
    @NotNull
    @Pattern(regexp = "^(?=\\S+$).{3,}$", message = "No whitespace")
    private String numbers;

    @Column(name = "status")
    private String status;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="id_client")
    @JsonBackReference
    private Owner owner;

    public Vehicle()
    {
    }

    public Vehicle(String brand, String numbers, String status,Owner owner)
    {
        this.brand = brand;
        this.numbers = numbers;
        this.status= status;
        this.owner = owner;
    }

    public String getBrand()
    {
        return brand;
    }

    public void setBrand(String brand)
    {
        this.brand = brand;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getNumbers()
    {
        return numbers;
    }

    public void setNumbers(String numbers)
    {
        this.numbers = numbers;
    }

    public Owner getOwner()
    {
        return owner;
    }

    public void setOwner(Owner owner)
    {
        this.owner = owner;
    }

    @Override
    public String toString()
    {
        return "Vehicle{" +
                ", brand='" + brand + '\'' +
                ", numbers='" + numbers + '\'' +
                ", status='" + status + '\'' +
                ", owner=" + owner +
                '}';
    }
}