package com.kindsonthegenius.fleetmsv2.fleet.controllers;

import com.kindsonthegenius.fleetapp_v2.fleet.models.VehicleType;
import com.kindsonthegenius.fleetapp_v2.fleet.services.VehicleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class VehicleTypeController {
	
	@Autowired private VehicleTypeService vehicleTypeService;
	
	//Get All VehicleTypes
	@GetMapping("/fleet/vehicleTypes")
	public String findAll(Model model){		
		model.addAttribute("vehicleTypes", vehicleTypeService.findAll());
		return "/fleet/vehicleTypes";
	}	
	
	@RequestMapping("/fleet/vehicleType/{id}")
	@ResponseBody
	public Optional<VehicleType> findById(@PathVariable Integer id)
	{
		return vehicleTypeService.findById(id);
	}
	
	//Add VehicleType
	@PostMapping(value="/fleet/vehicleTypes")
	public String addNew(VehicleType vehicleType) {
		vehicleTypeService.save(vehicleType);
		return "redirect:/fleet/vehicleTypes";
	}	

	@RequestMapping(value="fleet/vehicleType/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(@PathVariable Integer id) {
		vehicleTypeService.delete(id);
		return "redirect:/fleet/vehicleTypes";
	}

}
