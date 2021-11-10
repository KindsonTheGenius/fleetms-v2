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
	
	@Autowired private StateService stateService;
	@Autowired private CountryService countryService;
	@Autowired private ClientService clientService;
	
	//Get All Clients
	@GetMapping("/parameters/clients")
	public String findAll(Model model){		
		model.addAttribute("countries", countryService.findAll());
		model.addAttribute("states", stateService.findAll());
		model.addAttribute("clients", clientService.findAll());
		return "/parameters/clients";
	}	
	
	@RequestMapping("clients/findById") 
	@ResponseBody
	public Optional<Client> findById(Integer id)
	{
		return clientService.findById(id);
	}

	@GetMapping("/parameters/clientAdd")
	public String clientAdd(){
		return "/parameters/clientAdd";
	}

	//Add Client
	@PostMapping(value="clients/addNew")
	public String addNew(Client client) {
		clientService.save(client);
		return "redirect:/parameters/clients";
	}	
	
	@RequestMapping(value="clients/update", method = {RequestMethod.PUT, RequestMethod.GET})
	public String update(Client client) {
		clientService.save(client);
		return "redirect:/parameters/clients";
	}
	
	@RequestMapping(value="clients/delete", method = {RequestMethod.DELETE, RequestMethod.GET})	
	public String delete(Integer id) {
		clientService.delete(id);
		return "redirect:/clients";
	}

}
