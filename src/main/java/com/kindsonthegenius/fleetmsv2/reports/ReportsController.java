package com.kindsonthegenius.fleetmsv2.reports;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReportsController {

    @GetMapping("/reports/vehicles")
    public String vehicles(){
        return "/reports/vehicles";
    }

    @GetMapping("/reports/accounts")
    public String accounts(){
        return "/reports/accounts";
    }

    @GetMapping("/reports/hires")
    public String hires(){
        return "/reports/hires";
    }

    @GetMapping("/reports/helpdesk")
    public String helpdesk(){
        return "/reports/helpdesk";
    }

    @GetMapping("/reports/maintenance")
    public String maintenance(){
        return "/reports/maintenance";
    }
}
