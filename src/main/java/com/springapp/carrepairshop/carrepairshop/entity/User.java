package com.springapp.carrepairshop.carrepairshop.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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
    @NotNull(message = "Username can't be empty")
    @Pattern(regexp = "^(?=\\S+$).{2,}$", message = "No whitespace")
    @Size(min = 2, max = 25)
    private String username;

    @Column(name = "password")
    @NotNull(message = "Password can't be empty")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{3,}$",message = "A-Z, a-z, 0-9, Special Character: @ # $ % ^ & + = , no whitespace, min 3 char.")
    private String password;

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

    @OneToMany(mappedBy = "user",cascade = {CascadeType.PERSIST,CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JsonManagedReference
    private List<Owner> owners;

    @OneToMany(mappedBy="user",cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JsonManagedReference
    private List<Message> userMessages;

    @ManyToMany(fetch = FetchType.LAZY, cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
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
