package com.springapp.carrepairshop.carrepairshop.controller;

import com.springapp.carrepairshop.carrepairshop.dao.OwnerRepository;
import com.springapp.carrepairshop.carrepairshop.dao.VehicleRepository;
import com.springapp.carrepairshop.carrepairshop.entity.Owner;
import com.springapp.carrepairshop.carrepairshop.entity.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping("/vehicle")
public class VehicleController
{
    private VehicleRepository vehicleRepository;
    private OwnerRepository ownerRepository;

    @Autowired
    public VehicleController(VehicleRepository vehicleRepository, OwnerRepository ownerRepository)
    {
        this.vehicleRepository = vehicleRepository;
        this.ownerRepository = ownerRepository;
    }

    @GetMapping("/add")
    public String addVehicle(@RequestParam("ownerId")int id, Model model)
    {
        Vehicle vehicle = new Vehicle();
        Owner owner = ownerRepository.findById(id).get();
        vehicle.setOwner(owner);

        model.addAttribute("vehicle",vehicle);
        return "vehicle/add-form";
    }

    @PostMapping("/save")
    public String saveVehicle(@ModelAttribute("vehicle")Vehicle vehicle)
    {
        Owner owner = vehicle.getOwner();
        owner.add(vehicle);
        vehicleRepository.save(vehicle);
        return "redirect:/owner/findAll";
    }

    @GetMapping("/update")
    public String updateVehicle(@RequestParam("vehicleId")int id,Model model)
    {
        model.addAttribute("vehicle",vehicleRepository.findById(id).get());
        return "vehicle/add-form";
    }

    @GetMapping("/delete")
    public String deleteVehicle(@RequestParam("vehicleId")int id)
    {
        vehicleRepository.deleteById(id);
        return "redirect:/owner/findAll";
    }
}
