package com.springapp.carrepairshop.carrepairshop.rest.controller;

import com.springapp.carrepairshop.carrepairshop.dao.OwnerRepository;
import com.springapp.carrepairshop.carrepairshop.dao.UserRepository;
import com.springapp.carrepairshop.carrepairshop.entity.Owner;
import com.springapp.carrepairshop.carrepairshop.entity.User;
import com.springapp.carrepairshop.carrepairshop.rest.handler.owner.OwnerNotFoundException;
import com.springapp.carrepairshop.carrepairshop.rest.handler.user.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class OwnerRestController
{
    private OwnerRepository ownerRepository;
    private UserRepository userRepository;

    @Autowired
    public OwnerRestController(OwnerRepository ownerRepository, UserRepository userRepository)
    {
        this.ownerRepository = ownerRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/owners")
    public ResponseEntity<List<Owner>> findAll()
    {
        return new ResponseEntity<>(ownerRepository.findAll(),HttpStatus.OK);
    }

    @GetMapping("/owners/{id}")
    public ResponseEntity<Owner> findOwnerById(@PathVariable int id)
    {
        Optional<Owner> result = ownerRepository.findById(id);

        if(!result.isPresent())
            throw new OwnerNotFoundException("There's no owner with id: " + id);
        else
            return new ResponseEntity<>(result.get(),HttpStatus.FOUND);
    }

    @PostMapping("/owners/{employeeId}")
    public ResponseEntity<Owner> addOwner(@RequestBody Owner owner, @PathVariable int employeeId)
    {
        Optional<User> user = userRepository.findById(employeeId);
        if(!user.isPresent())
            throw new UserNotFoundException("There's no Employee with id: "+ employeeId);
        user.get().addOwner(owner);
        ownerRepository.save(owner);
        return new ResponseEntity<>(owner,HttpStatus.CREATED);
    }

    @PutMapping("/owners")
    public ResponseEntity<Owner> updateOwner(@RequestBody Owner owner)
    {
        Optional<Owner> result = ownerRepository.findById(owner.getId());
        if(!result.isPresent())
            throw new OwnerNotFoundException("There's no Owner with id: " + owner.getId());
        else
        {
            result.get().setFirstName(owner.getFirstName());
            result.get().setLastName(owner.getLastName());
            ownerRepository.save(result.get());
            return new ResponseEntity<>(result.get(),HttpStatus.OK);
        }
    }

    @DeleteMapping("/owners/{id}")
    public ResponseEntity<List<Owner>> deleteOwnerById(@PathVariable int id)
    {
        Optional<Owner> result = ownerRepository.findById(id);

        if (!result.isPresent())
            throw new OwnerNotFoundException("There's no Owner with id: "+ id);
        else
            ownerRepository.deleteById(id);
        return new ResponseEntity<>(ownerRepository.findAll(),HttpStatus.OK);
    }
}
