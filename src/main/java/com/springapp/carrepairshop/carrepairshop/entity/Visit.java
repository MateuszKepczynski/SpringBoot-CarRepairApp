package com.springapp.carrepairshop.carrepairshop.entity;

import javax.persistence.*;

@Entity
@Table(name = "visit")
public class Visit extends BaseEntity
{
    @Column(name = "date")
    private String date;

    @Column(name = "description")
    private String description;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "id_car")
    private Vehicle vehicle;

    public Visit() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
