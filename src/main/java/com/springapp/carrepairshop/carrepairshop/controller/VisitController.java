package com.springapp.carrepairshop.carrepairshop.controller;

import com.springapp.carrepairshop.carrepairshop.dao.VehicleRepository;
import com.springapp.carrepairshop.carrepairshop.dao.VisitRepository;
import com.springapp.carrepairshop.carrepairshop.entity.Vehicle;
import com.springapp.carrepairshop.carrepairshop.entity.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/visit")
public class VisitController
{
    private VisitRepository visitRepository;
    private VehicleRepository vehicleRepository;

    @Autowired
    public VisitController(VisitRepository visitRepository, VehicleRepository vehicleRepository)
    {
        this.visitRepository = visitRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @GetMapping("/update")
    public String update(@RequestParam("visitId")int id, Model model)
    {
        model.addAttribute("visit",visitRepository.findById(id).get());
        return "vehicle/visit/add-form";
    }

    @GetMapping("/add")
    public String add(@RequestParam("vehicleId")int id, Model model)
    {
        Visit visit = new Visit();
        visit.setVehicle(vehicleRepository.findById(id).get());
        model.addAttribute("visit",visit);
        return "vehicle/visit/add-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("visit")Visit visit)
    {
        Vehicle vehicle = visit.getVehicle();
        vehicle.add(visit);
        visitRepository.save(visit);
        return "redirect:/owner/findAll"; /*TODO redirect on owner profile*/
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("visitId")int id)
    {
        visitRepository.deleteById(id);
        return "redirect:/owner/findAll";
    }
}
