package com.springapp.carrepairshop.carrepairshop.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "owner")
public class Owner
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    @NotNull(message = "First Name can't be empty")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{2,}$", message = "No whitespace")
    @Size(min = 2,max = 35)
    private String firstName;

    @Column(name = "last_name")
    @NotNull(message = "Last Name can't be empty")
    @Pattern(regexp = "^(?=\\S+$).{2,}$", message = "No whitespace")
    @Size(min = 2,max = 35)
    private String lastName;

    @Column(name = "address")
    @NotNull(message = "Address can't be empty")
    @Size(min = 2,max = 45)
    private String address;

    @Column(name = "phone_number")
    @NotNull(message = "Phone Number can't be empty")
    @Size(min = 9,max = 16)
    private String phoneNumber;

    @Column(name = "city")
    @NotNull
    @Size(min = 2,max = 50)
    private String city;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,
            CascadeType.REFRESH})
    @JoinColumn(name = "id_employee")
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy="owner",
            cascade= {CascadeType.ALL})
    @JsonManagedReference
    private List<Vehicle> vehicles;


    public Owner()
    {
    }

    public Owner(int id, String firstName, String lastName, String address, String phoneNumber, String city)
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.city = city;
    }

    public Owner(String firstName, String lastName, String address, String phoneNumber, String city, User user, List<Vehicle> vehicles)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.user = user;
        this.vehicles = vehicles;
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

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    @Override
    public String toString()
    {
        return "Owner{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", city='" + city + '\'' +
                '}';
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

