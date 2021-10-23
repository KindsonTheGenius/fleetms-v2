package com.kindsonthegenius.fleetmsv2.fleet.controllers;

import com.kindsonthegenius.fleetapp_v2.fleet.models.VehicleMaintenance;
import com.kindsonthegenius.fleetapp_v2.fleet.services.VehicleMaintenanceService;
import com.kindsonthegenius.fleetapp_v2.fleet.services.VehicleService;
import com.kindsonthegenius.fleetapp_v2.parameters.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class VehicleMaintenanceController {

	@Autowired private VehicleMaintenanceService vehicleMaintenanceService;
	@Autowired private VehicleService vehicleService;
	@Autowired private SupplierService supplierService;

	public Model addModelAttributes(Model model){
		model.addAttribute("vehicles", vehicleService.findAll());
		model.addAttribute("suppliers", supplierService.findAll());
		return model;
	}
	
	//Get All VehicleMaintenances
	@GetMapping("/fleet/maintenances")
	public String findAll(Model model){		
		model.addAttribute("maintenances", vehicleMaintenanceService.findAll());
		return "/fleet/maintenances";
	}

	@GetMapping("/fleet/maintenanceAdd")
	public String addMaintenance(Model model){
		addModelAttributes(model);
		return "/fleet/maintenanceAdd";
	}

	@GetMapping("/fleet/maintenance/{op}/{id}")
	public String editMaintenance(Model model, @PathVariable Integer id, @PathVariable String op){
		VehicleMaintenance maintenance = vehicleMaintenanceService.findById(id);
		model.addAttribute("maintenance", maintenance);
		addModelAttributes(model);
		return "/fleet/maintenance"+op;
	}

	//Add VehicleMaintenance
	@PostMapping("/fleet/maintenances")
	public String addNew(VehicleMaintenance vehicleMaintenance) {
		vehicleMaintenanceService.save(vehicleMaintenance);
		return "redirect:/fleet/maintenances";
	}
	
	@RequestMapping(value="fleet/maintenance/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(@PathVariable Integer id) {
		vehicleMaintenanceService.delete(id);
		return "redirect:/fleet/maintenances";
	}

}
