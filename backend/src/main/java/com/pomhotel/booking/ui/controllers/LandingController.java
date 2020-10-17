package com.pomhotel.booking.ui.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LandingController {
    //--- Home Mappings -----------------------------------------------------
    @GetMapping("/")
    public String landingController() {
        //return "redirect:/home";
        return "landing";
    }
}
