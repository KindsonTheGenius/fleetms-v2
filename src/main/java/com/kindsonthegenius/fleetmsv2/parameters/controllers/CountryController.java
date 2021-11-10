package com.kindsonthegenius.fleetmsv2.parameters.controllers;

import com.kindsonthegenius.fleetmsv2.parameters.models.Country;
import com.kindsonthegenius.fleetmsv2.parameters.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping("/parameters/countries")
    public String  getAll(Model model){
        List<Country> countries =   countryService.findAll();
        model.addAttribute("countries", countries);
        return "/parameters/countries";
    }

    @GetMapping("/parameters/countryAdd")
    public String addCountry(){
        return "parameters/countryAdd";
    }

    //The op parameter is either Edit or Details
    @GetMapping("/parameters/country/{op}/{id}")
    public String editCountry(@PathVariable Integer id, @PathVariable String op, Model model){
        Country country = countryService.getById(id);
        model.addAttribute("country", country);
        return "/parameters/country"+ op;
    }

    @PostMapping("/parameters/countries")
    public String save(Country country){
        countryService.save(country);
        return "redirect:/parameters/countries";
    }

    @RequestMapping(value = "/parameters/countries/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public  String delete(@PathVariable Integer id){
        countryService.delete(id);
        return "redirect:/parameters/countries";
    }

}
