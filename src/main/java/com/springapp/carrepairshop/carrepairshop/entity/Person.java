package com.springapp.carrepairshop.carrepairshop.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@MappedSuperclass
public class Person extends BaseEntity
{
    @Column(name = "first_name")
    @NotNull(message = "First Name can't be empty")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{2,}$", message = "Wrong First Name")
    @Size(min = 2,max = 35)
    private String firstName;

    @Column(name = "last_name")
    @NotNull(message = "Last Name can't be empty")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{2,}$", message = "Wrong Last Name")
    @Size(min = 2,max = 50)
    private String lastName;

    @Column(name = "address")
    @NotNull(message = "Address can't be empty")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[-]).{2,}$",message = "Wrong Address name")
    @Size(min = 2,max = 45)
    private String address;

    @Column(name = "phone_number")
    @NotNull(message = "Phone Number can't be empty")
    @Size(min = 9,max = 16)
    private String phoneNumber;

    @Column(name = "city")
    @NotNull(message = "City can't be empty")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z]).{2,}$", message = "Wrong City name")
    @Size(max = 20)
    private String city;

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
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
