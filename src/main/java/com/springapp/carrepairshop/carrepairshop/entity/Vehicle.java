package com.springapp.carrepairshop.carrepairshop.entity;

import javax.persistence.*;

@Entity
@Table(name = "vehicle")
public class Vehicle
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "brand")
    private String brand;

    @Column(name = "numbers")
    private String numbers;

    @Column(name = "status")
    private String status;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="id_client")
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

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
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
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", numbers='" + numbers + '\'' +
                ", status='" + status + '\'' +
                ", owner=" + owner +
                '}';
    }
}