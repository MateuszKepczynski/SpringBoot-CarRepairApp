package com.springapp.carrepairshop.carrepairshop.controller;

import com.springapp.carrepairshop.carrepairshop.entity.Owner;
import com.springapp.carrepairshop.carrepairshop.dao.UserRepository;
import com.springapp.carrepairshop.carrepairshop.entity.User;
import com.springapp.carrepairshop.carrepairshop.dao.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/owner")
public class OwnerController
{
    private UserRepository userRepository;

    private OwnerRepository ownerRepository;

    @Autowired
    public OwnerController(OwnerRepository ownerRepository, UserRepository userRepository)
    {
        this.userRepository = userRepository;
        this.ownerRepository = ownerRepository;
    }

    @GetMapping("/showFindForm")
    public String findOwnerByLastName(Model model)
    {
        model.addAttribute("owner", new Owner());
        return "owner/owner-form";
    }

    @PostMapping("/findByLastName")
    public String findByLastName(@ModelAttribute("owner") Owner owner, Model model)
    {
        List<Owner> result = ownerRepository.findOwnerByLastName(owner.getLastName());

        if (result.isEmpty())
        {
            model.addAttribute("findError", "No Owner with Last Name: " + owner.getLastName());
            return "owner/owner-form";
        }
        model.addAttribute("ownerList", result);
        return "owner/show-owner-list";
    }

    @GetMapping("/findAll")
    public String findAll(Model model)
    {
        model.addAttribute("ownerList", ownerRepository.findAll());
        return "owner/show-owner-list";
    }

    @GetMapping("/add")
    public String addOwner(Model model)
    {
        model.addAttribute("employee", userRepository.findAll());
        model.addAttribute("owner", new Owner());
        return "owner/add-form";
    }

    @PostMapping("/save")
    public String saveOwner(@Valid @ModelAttribute("owner") Owner owner, BindingResult bindingResult, Model model)
    {
        if (bindingResult.hasErrors())
        {
            model.addAttribute("employee", userRepository.findAll());
            return "owner/add-form";
        }
        User user = userRepository.findUserByLastName(owner.getUser().getLastName()).get(0);
        user.addOwner(owner);
        ownerRepository.save(owner);
        return "redirect:/owner/findAll";
    }

    @GetMapping("/showProfile")
    public String showOwnerProfile(@RequestParam("ownerId") int id, Model model)
    {
        model.addAttribute("owner", ownerRepository.findById(id).get());
        return "owner/show-profile";
    }

    @GetMapping("/update")
    public String updateOwner(@RequestParam("ownerId") int id, Model model)
    {
        model.addAttribute("employee", userRepository.findAll());
        model.addAttribute("owner", ownerRepository.findById(id).get());
        return "owner/add-form";
    }

    @GetMapping("/delete")
    public String deleteOwner(@RequestParam("ownerId") int id)
    {
        Owner owner = ownerRepository.findById(id).get();
        ownerRepository.delete(owner);
        return "redirect:/owner/findAll";
    }
}
