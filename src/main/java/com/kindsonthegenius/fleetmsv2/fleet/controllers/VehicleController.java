package com.kindsonthegenius.fleetmsv2.fleet.controllers;

import com.kindsonthegenius.fleetapp_v2.fleet.models.Vehicle;
import com.kindsonthegenius.fleetapp_v2.fleet.services.*;
import com.kindsonthegenius.fleetapp_v2.hr.services.EmployeeService;
import com.kindsonthegenius.fleetapp_v2.parameters.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class VehicleController {
	
	@Autowired private VehicleService vehicleService;
	@Autowired private VehicleTypeService vehicleTypeService;
	@Autowired private VehicleMakeService vehicleMakeService;
	@Autowired private VehicleModelService vehicleModelService;
	@Autowired private LocationService locationService;
	@Autowired private EmployeeService employeeService ;
	@Autowired private VehicleStatusService vehicleStatusService;

	public Model addModelAttributes(Model model){
		model.addAttribute("vehicles", vehicleService.findAll());
		model.addAttribute("vehicleTypes", vehicleTypeService.findAll());
		model.addAttribute("vehicleModels", vehicleModelService.findAll());
		model.addAttribute("vehicleMakes", vehicleMakeService.findAll());
		model.addAttribute("locations", locationService.findAll());
		model.addAttribute("employees", employeeService.findAll());
		model.addAttribute("vehicleStatuses", vehicleStatusService.findAll());
		return model;
	}

	//Get All Vehicles
	@GetMapping("/fleet/vehicles")
	public String findAll(Model model){		
		addModelAttributes(model);
		return "/fleet/vehicles";
	}

	@GetMapping("/fleet/vehicleAdd")
	public String addVehicle(Model model){
		addModelAttributes(model);
		return "fleet/vehicleAdd";
	}

	//The op parameter is either Edit or Details
	@GetMapping("/fleet/vehicle/{op}/{id}")
	public String editVehicle(@PathVariable Integer id, @PathVariable String op, Model model){
		Vehicle vehicle = vehicleService.findById(id);
		model.addAttribute("vehicle", vehicle);
		addModelAttributes(model);
		return "/fleet/vehicle"+ op; //returns vehicleEdit or vehicleDetails
	}

	//Add Vehicle
	@PostMapping("/fleet/vehicles")
	public String addNew(Vehicle vehicle) {
		vehicleService.save(vehicle);
		return "redirect:/fleet/vehicles";
	}	

	@RequestMapping(value="/fleet/vehicle/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(@PathVariable Integer id) {
		vehicleService.delete(id);
		return "redirect:/fleet/vehicles";
	}
}
