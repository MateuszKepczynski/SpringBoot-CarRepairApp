package com.springapp.carrepairshop.carrepairshop.rest.controller;

import com.springapp.carrepairshop.carrepairshop.dao.UserRepository;
import com.springapp.carrepairshop.carrepairshop.entity.User;
import com.springapp.carrepairshop.carrepairshop.rest.handler.user.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserRestController
{
    private UserRepository userRepository;

    @Autowired
    public UserRestController(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> findAll()
    {
        return new ResponseEntity<>(userRepository.findAll(),HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> findUserById(@PathVariable int id)
    {
        Optional<User> user = userRepository.findById(id);

        if (!user.isPresent())
            throw new UserNotFoundException("There's no Employee with id: "+ id);
        else
            return new ResponseEntity<>(user.get(), HttpStatus.FOUND);
    }

    @PostMapping(value = "/users",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> addUser(@RequestBody User user)
    {
        userRepository.save(user);
        return new ResponseEntity<>(user,HttpStatus.CREATED);
    }

    @PutMapping("/users")
    public ResponseEntity<User> updateUser(@RequestBody User user)
    {
        Optional<User> result = userRepository.findById(user.getId());
        if (!result.isPresent())
            throw new UserNotFoundException("There's no Employee with id: "+user.getId());
        else
        {
            result.get().setFirstName(user.getFirstName());
            result.get().setLastName(user.getLastName());
        }
        userRepository.save(result.get());
        return new ResponseEntity<>(result.get(),HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<List<User>> deleteUserById(@PathVariable int id)
    {
        Optional<User> result = userRepository.findById(id);

        if (!result.isPresent())
            throw new UserNotFoundException("There's no Employee with id: " + id);
        else
            userRepository.deleteById(id);

        return new ResponseEntity<>(userRepository.findAll(),HttpStatus.OK);
    }
}
