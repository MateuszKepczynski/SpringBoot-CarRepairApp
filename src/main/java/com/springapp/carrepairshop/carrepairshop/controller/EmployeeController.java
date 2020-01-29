package com.springapp.carrepairshop.carrepairshop.controller;

import com.springapp.carrepairshop.carrepairshop.dao.OwnerRepository;
import com.springapp.carrepairshop.carrepairshop.dao.RoleRepository;
import com.springapp.carrepairshop.carrepairshop.entity.User;
import com.springapp.carrepairshop.carrepairshop.dao.UserRepository;
import com.springapp.carrepairshop.carrepairshop.validation.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.Arrays;

@Controller
@RequestMapping("/employee")
public class EmployeeController
{
    private BCryptPasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private OwnerRepository ownerRepository;
    private RoleRepository roleRepository;

    @Autowired
    public EmployeeController(UserRepository userRepository, OwnerRepository ownerRepository, RoleRepository roleRepository,BCryptPasswordEncoder passwordEncoder)
    {
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.ownerRepository = ownerRepository;
    }

    @GetMapping("/showAll")
    public String showAll(Model model)
    {
        model.addAttribute("employeeList",userRepository.findAll());
        return "employee/show-all";
    }

    @GetMapping("/showProfile")
    public String showEmployeeProfile(@RequestParam("employeeId") int id, Model model)
    {
        model.addAttribute("clients", ownerRepository.findOwnerByUserId(id));
        model.addAttribute("employee",userRepository.findById(id).get());
        return "employee/show-profile";
    }

    @GetMapping("/showEmployeePanel")
    public String addEmployee(Model model)
    {
        model.addAttribute("employee",new User());
        return "admin/employee/add-form";
    }

    @PostMapping("/save") //TODO ADD ROLE SELECT MENU
    public String save(@ModelAttribute("employee") User user, Model model)
    {
        UserValidation userValidation = new UserValidation();

        User result = userRepository.findUserByUsername(user.getUsername());

        if(result == null)
        {
            if((userValidation.validateUser(user)) && (userRepository.findUserByUsername(user.getUsername()) == null))
            {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                user.setRole(Arrays.asList(roleRepository.findRoleByName("ROLE_EMPLOYEE")));
                userRepository.save(user);
                return "redirect:/employee/showAll";
            }
            model.addAttribute("saveError","Invalid data");
            return "admin/employee/add-form";
        }
        userRepository.save(user);
        return "redirect:/employee/showAll";

    }

    @GetMapping("/update")
    public String updateEmployee(@RequestParam("employeeId")int id, Model model)
    {
        model.addAttribute("employee",userRepository.findById(id).get());
        return "admin/employee/add-form";
    }

    @GetMapping("/delete")
    public String deleteEmployeeById(@RequestParam("employeeId")int id)
    {
        userRepository.deleteById(id);
        return "redirect:/employee/showAll";
    }
}
