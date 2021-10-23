package com.kindsonthegenius.fleetmsv2.hr.controllers;

import com.kindsonthegenius.fleetmsv2.hr.models.EmployeeStatus;
import com.kindsonthegenius.fleetmsv2.hr.services.EmployeeStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeStatusController {

    @Autowired
    private EmployeeStatusService employeeStatusService;

    @GetMapping("/hr/employeeStatuses")
    public String parameters(Model model){
        List<EmployeeStatus> employeeStatuses = employeeStatusService.findAll();
        model.addAttribute("employeeStatuses", employeeStatuses);
        return "hr/employeeStatuses";
    }

    //Get Job Title by id
    @GetMapping("/hr/employeeStatus/{id}")
    @ResponseBody
    public EmployeeStatus getById(@PathVariable Integer id){
        return employeeStatusService.findById(id).orElse(null);
    }

    @PostMapping("/hr/employeeStatuses")
    public String save(EmployeeStatus employeeStatus){
        employeeStatusService.save(employeeStatus);
        return "redirect:/hr/employeeStatuses";
    }

    @RequestMapping(value="/hr/employeeStatus/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable Integer id) {
        employeeStatusService.delete(id);
        return "redirect:/hr/employeeStatuses";
    }
    
}
