package com.springapp.carrepairshop.carrepairshop.controller;

import com.springapp.carrepairshop.carrepairshop.dao.OwnerRepository;
import com.springapp.carrepairshop.carrepairshop.dao.RoleRepository;
import com.springapp.carrepairshop.carrepairshop.entity.User;
import com.springapp.carrepairshop.carrepairshop.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.Arrays;

@Controller
@RequestMapping("/employee")
public class EmployeeController
{
    @InitBinder
    public void initBinder(WebDataBinder binder) //trim whitespaces
    {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    private BCryptPasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private OwnerRepository ownerRepository;
    private RoleRepository roleRepository;

    @Autowired
    public EmployeeController(UserRepository userRepository, OwnerRepository ownerRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder)
    {
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.ownerRepository = ownerRepository;
    }

    @GetMapping("/showAll")
    public String showAll(Model model)
    {
        model.addAttribute("employeeList", userRepository.findAll());
        return "employee/show-all";
    }

    @GetMapping("/showProfile")
    public String showEmployeeProfile(@RequestParam("employeeId") int id, Model model)
    {
        model.addAttribute("clients", ownerRepository.findOwnerByUserId(id));
        model.addAttribute("employee", userRepository.findById(id).get());
        return "employee/show-profile";
    }

    @GetMapping("/showEmployeePanel")
    public String addEmployee(Model model)
    {
        User user = new User();
        model.addAttribute("employee", user);
        return "admin/employee/add-form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("employee") User user, BindingResult bindingResult,Model model)
    {
        if (bindingResult.hasErrors())
        {
            return "admin/employee/add-form";
        }

        if (user.getId() == 0) //check user in db, if id == 0 then hes not in db
        {
            if (userRepository.findUserByUsername(user.getUsername()) == null) //check username is available
            {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                user.setRole(Arrays.asList(roleRepository.findRoleByName("ROLE_EMPLOYEE")));
                userRepository.save(user);
                return "redirect:/employee/showAll";
            } else
            {
                model.addAttribute("saveError", "Username taken");
                return "admin/employee/add-form";
            }
        }
        if (user.getUsername() == userRepository.findById(user.getId()).get().getUsername() || userRepository.findUserByUsername(user.getUsername()) == null)
        {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return "redirect:/employee/showAll";
        }
        else
        {
            model.addAttribute("saveError","Username taken");
            return "admin/employee/add-form";
        }

    }

    @GetMapping("/update")
    public String updateEmployee(@RequestParam("employeeId") int id, Model model)
    {
        User user = userRepository.findById(id).get();
        model.addAttribute("employee", user);
        return "admin/employee/add-form";
    }

    @GetMapping("/delete")
    public String deleteEmployeeById(@RequestParam("employeeId") int id)
    {
        userRepository.deleteById(id);
        return "redirect:/employee/showAll";
    }
}
