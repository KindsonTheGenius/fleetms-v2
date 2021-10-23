package com.kindsonthegenius.fleetmsv2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationController {

    @GetMapping("/index")
    public  String home(){
        return  "index";
    }

    @GetMapping("/widgets")
    public  String widget(){
        return  "widgets";
    }

}
