package com.kindsonthegenius.fleetmsv2.parameters.controllers;

import com.kindsonthegenius.fleetmsv2.parameters.models.Contact;
import com.kindsonthegenius.fleetmsv2.parameters.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ContactController {

	@Autowired
	private ContactService contactService;

	@GetMapping("/parameters/contacts")
	public String getAll(Model model){
		List<Contact> contacts =   contactService.findAll();
		model.addAttribute("contacts", contacts);
		return "/parameters/contacts";
	}

	//The Get Contact By Id
	@GetMapping("/parameters/contact/{id}")
	@ResponseBody
	public Contact getContact(@PathVariable Integer id){
		return contactService.findById(id);
	}

	@GetMapping("/parameters/contactAdd")
	public String addContact(){
		return "parameters/contactAdd";
	}

	//The op parameter is either Edit or Details
	@GetMapping("/parameters/contact/{op}/{id}")
	public String editContact(@PathVariable Integer id, @PathVariable String op, Model model){
		Contact contact = contactService.findById(id);
		model.addAttribute("contact", contact);
		return "/parameters/contact"+ op;
	}

	@PostMapping("/parameters/contacts")
	public String save(Contact contact){
		contactService.save(contact);
		return "redirect:/parameters/contacts";
	}

	@RequestMapping(value = "/parameters/contacts/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
	public  String delete(@PathVariable Integer id){
		contactService.delete(id);
		return "redirect:/parameters/contacts";
	}
}
