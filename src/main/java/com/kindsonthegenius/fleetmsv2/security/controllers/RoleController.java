package com.kindsonthegenius.fleetmsv2.security.controllers;

import com.kindsonthegenius.fleetmsv2.security.models.Role;
import com.kindsonthegenius.fleetmsv2.security.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/security/roles")
    public String parameters(Model model) {
        List<Role> roles = roleService.findAll();
        model.addAttribute("roles", roles);
        return "security/roles";
    }

    @GetMapping("/security/role/{id}")
    @ResponseBody
    public Role getById(@PathVariable Integer id) {
        return roleService.findById(id);
    }

    @PostMapping("/security/roles")
    public String save(Role role) {
        roleService.save(role);
        return "redirect:/security/roles";
    }

    @RequestMapping(value = "/security/role/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable Integer id) {
        roleService.delete(id);
        return "redirect:/security/roles";
    }

}
