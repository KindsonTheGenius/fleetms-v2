package com.kindsonthegenius.fleetmsv2.parameters.controllers;

import com.kindsonthegenius.fleetmsv2.parameters.models.Country;
import com.kindsonthegenius.fleetmsv2.parameters.services.CountryService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CountryController {

    @Autowired
    private CountryService countryService;

//    @GetMapping("/parameters/countries")
//    public String  getAll(Model model, String keyword){
//        List<Country> countries;
//        countries = keyword == null? countryService.findAll():countryService.findByKeyword(keyword);
//        model.addAttribute("countries", countries);
//        return "/parameters/countries";
//    }

    @GetMapping("/parameters/countries")
    public String getAllPages(Model model){
        return getOnePage(model, 1);
    }

    @GetMapping("/parameters/countries/page/{pageNumber}")
    public String  getOnePage(Model model, @PathVariable("pageNumber") int currentPage){
        Page<Country> page = countryService.findPage(currentPage);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<Country> countries = page.getContent();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("countries", countries);

        return "/parameters/countries";
    }

    @GetMapping("/parameters/countries/page/{pageNumber}/{field}")
    public String getPageWithSort(Model model,
                                  @PathVariable("pageNumber") int currentPage,
                                  @PathVariable String field,
                                  @PathParam("sortDir") String sortDir) {

        Page<Country> page = countryService.findAllWithSort(field, sortDir, currentPage);
        List<Country> countries = page.getContent();
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);

        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("countries", countries);
        return "/parameters/countries";
    }

    //The Get Country By Id
    @GetMapping("/parameters/country/{id}")
    @ResponseBody
    public Country getCountry(@PathVariable Integer id){
        return countryService.getById(id);
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
