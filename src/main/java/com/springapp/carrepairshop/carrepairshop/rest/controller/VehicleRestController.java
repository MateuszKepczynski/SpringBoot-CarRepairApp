package com.springapp.carrepairshop.carrepairshop.rest.controller;

import com.springapp.carrepairshop.carrepairshop.dao.OwnerRepository;
import com.springapp.carrepairshop.carrepairshop.dao.VehicleRepository;
import com.springapp.carrepairshop.carrepairshop.entity.Owner;
import com.springapp.carrepairshop.carrepairshop.entity.Vehicle;
import com.springapp.carrepairshop.carrepairshop.rest.handler.owner.OwnerNotFoundException;
import com.springapp.carrepairshop.carrepairshop.rest.handler.vehicle.VehicleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class VehicleRestController
{
    private VehicleRepository vehicleRepository;
    private OwnerRepository ownerRepository;

    @Autowired
    public VehicleRestController(VehicleRepository vehicleRepository, OwnerRepository ownerRepository)
    {
        this.vehicleRepository = vehicleRepository;
        this.ownerRepository = ownerRepository;
    }

    @GetMapping("/vehicles")
    public ResponseEntity<List<Vehicle>> findAll()
    {
        return new ResponseEntity<>(vehicleRepository.findAll(),HttpStatus.FOUND);
    }

    @GetMapping("/vehicles/{id}")
    public ResponseEntity<Vehicle> findVehicleById(@PathVariable int id)
    {
        Optional<Vehicle> result = vehicleRepository.findById(id);
        if (!result.isPresent())
            throw new VehicleNotFoundException("There's no Vehicle with id: " + id);
        else
            return new ResponseEntity<>(result.get(), HttpStatus.FOUND);
    }

    @PostMapping("/vehicles/{ownerId}")
    public ResponseEntity<Vehicle> addVehicle(@RequestBody Vehicle vehicle, @PathVariable int ownerId)
    {
        Optional<Owner> owner = ownerRepository.findById(ownerId);
        if(!owner.isPresent())
            throw new OwnerNotFoundException("There's no Owner with id: "+ownerId);
        owner.get().add(vehicle);
        vehicleRepository.save(vehicle);
        return new ResponseEntity<>(vehicle,HttpStatus.CREATED);
    }

    @PutMapping("/vehicles")
    public ResponseEntity<Vehicle> updateVehicle(@RequestBody Vehicle vehicle)
    {
        Optional<Vehicle> result = vehicleRepository.findById(vehicle.getId());

        if (!result.isPresent())
            throw new VehicleNotFoundException("There's no Vehicle with id: " + vehicle.getId());
        else
        {
            result.get().setBrand(vehicle.getBrand());
            result.get().setNumbers(vehicle.getNumbers());
            vehicleRepository.save(result.get());
            return new ResponseEntity<>(result.get(),HttpStatus.OK);
        }
    }

    @DeleteMapping("/vehicles/{id}")
    public ResponseEntity<List<Vehicle>> deleteVehicleById(@PathVariable int id)
    {
        Optional<Vehicle> result = vehicleRepository.findById(id);

        if (!result.isPresent())
            throw new VehicleNotFoundException("There's no Vehicle with id: " + id);
        else
            vehicleRepository.deleteById(id);
        return new ResponseEntity<>(vehicleRepository.findAll(),HttpStatus.OK);
    }
}
