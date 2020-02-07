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
public class User extends Person
{
    @Column(name = "username")
    @NotNull(message = "Username can't be empty")
    @Pattern(regexp = "^(?=\\S+$).{2,}$", message = "No whitespace")
    @Size(min = 2, max = 25)
    private String username;

    @Column(name = "password")
    @NotNull(message = "Password can't be empty")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{3,}$",message = "A-Z, a-z, 0-9, Special Character: @ # $ % ^ & + = , no whitespace, min 3 char.")
    private String password;

    @JsonManagedReference
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Owner> owners;

    @JsonManagedReference
    @OneToMany(mappedBy="user",cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
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

    public Collection<Role> getRole()
    {
        return role;
    }

    public void setRole(Collection<Role> role)
    {
        this.role = role;
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
