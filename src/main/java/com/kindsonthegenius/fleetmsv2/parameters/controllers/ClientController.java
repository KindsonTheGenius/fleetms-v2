package com.kindsonthegenius.fleetmsv2.parameters.controllers;

import com.kindsonthegenius.fleetmsv2.parameters.models.Client;
import com.kindsonthegenius.fleetmsv2.parameters.services.ClientService;
import com.kindsonthegenius.fleetmsv2.parameters.services.CountryService;
import com.kindsonthegenius.fleetmsv2.parameters.services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class ClientController {

	@Autowired	private ClientService clientService;
	@Autowired	private CountryService countryService;
	@Autowired	private StateService stateService;

	public Model addModelAttributes(Model model){
		model.addAttribute("clients", clientService.findAll());
		model.addAttribute("countries", countryService.findAll());
		model.addAttribute("states", stateService.findAll());
		return model;
	}

	@GetMapping("/parameters/clients")
	public String findAll(Model model){
		addModelAttributes(model);
		return "/parameters/clients";
	}

	@GetMapping("/parameters/clientAdd")
	public String addClient(Model model){
		model.addAttribute("countries", countryService.findAll());
		return "parameters/clientAdd";
	}

	//The op parameter is either Edit or Details
	@GetMapping("/parameters/client/{op}/{id}")
	public String editClient(@PathVariable Integer id, @PathVariable String op, Model model){
		Client client = clientService.findById(id);
		model.addAttribute("client", client);
		addModelAttributes(model);
		return "/parameters/client"+ op; //returns clientEdit or clientDetails
	}

	@PostMapping("/parameters/clients")
	public String save(Client client) {
		clientService.save(client);
		return "redirect:/parameters/clients";
	}

	@RequestMapping(value="/parameters/clients/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String deleteById(@PathVariable Integer id) {
		clientService.deleteById(id);
		return "redirect:/parameters/clients";
	}

}
