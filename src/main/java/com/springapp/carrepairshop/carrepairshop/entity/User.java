package com.springapp.carrepairshop.carrepairshop.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "city")
    private String city;

    @OneToMany(mappedBy = "user",cascade = {CascadeType.PERSIST,CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JsonManagedReference
    private List<Owner> owners;

    @OneToMany(mappedBy="user",cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JsonManagedReference
    private List<Message> userMessages;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> role;

    public User()
    {
    }

    public User(String username, String password, String firstName, String lastName, String address, String phoneNumber, String city)
    {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.city = city;
    }

    public User(String username, String password, String firstName, String lastName, String address, String phoneNumber,String city,
                Collection<Role> role,List<Message> userMessages,List<Owner> owner)
    {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.city=city;
        this.role = role;
        this.userMessages = userMessages;
        this.owners = owner;
    }

    public List<Owner> getOwners()
    {
        return owners;
    }

    public void setOwners(List<Owner> owner)
    {
        this.owners = owner;
    }

    public List<Message> getUserMessages()
    {
        return userMessages;
    }

    public void setUserMessages(List<Message> userMessages)
    {
        this.userMessages = userMessages;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
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

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
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

    public Collection<Role> getRole()
    {
        return role;
    }

    public void setRole(Collection<Role> role)
    {
        this.role = role;
    }

    @Override
    public String toString()
    {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", city='" + city + '\'' +
                ", owner=" + owners +
                ", userMessages=" + userMessages +
                ", role=" + role +
                '}';
    }

    public void add(Message userMessage)
    {
        if (userMessages == null)
            userMessages = new ArrayList<>();
        userMessages.add(userMessage);
        userMessage.setUser(this);
    }

    public void addOwner(Owner owner)
    {
        if(owners == null)
            owners = new ArrayList<>();
        owners.add(owner);
        owner.setUser(this);
    }
}
