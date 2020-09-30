package com.pomhotel.booking.ui.controllers;

import com.pomhotel.booking.application.models.BookingsModel;
import com.pomhotel.booking.application.services.BookingsService;
import com.pomhotel.booking.application.services.ClientLoginService;
import com.pomhotel.booking.ui.dto.NewBookingDTO;
import com.pomhotel.booking.application.models.RoomsModel;
import com.pomhotel.booking.application.models.RoomtypesModel;
import com.pomhotel.booking.application.services.RoomTypesService;
import com.pomhotel.booking.application.services.RoomsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class HomeController {
    RoomsService roomsService;
    RoomTypesService roomTypesService;

    @Autowired
    public HomeController(RoomsService roomsService, RoomTypesService roomTypesService) {
        this.roomsService = roomsService;
        this.roomTypesService = roomTypesService;
    }

    //HOME PAGE
    @GetMapping("/")
    public String index(Model model){
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("strNav", "Find Emotions");
        model.addAttribute("imgNav", "chica_piscina.jpg");
        return "home";
    }

    //ROOM PAGE
    @GetMapping("/rooms")
    public String roomsList(Model model){
        model.addAttribute("strNav", "Find your rest");
        model.addAttribute("imgNav", "revato-10251-13112723-111323.jpg");

        List<RoomsModel> rooms = roomsService.findApplyingFilter();
        model.addAttribute("rooms", rooms);
        model.addAttribute("roomy", new RoomsModel());

        List<RoomtypesModel> types = roomTypesService.findAll();
        model.addAttribute("types", types);

        return "listrooms";
    }

    @PostMapping("/rooms")
    public String roomsList() {
        return "redirect:/rooms";
    }


    //ABOUT US
    @GetMapping("/aboutus")
    public String aboutus(Model model){
        return "redirect:/acercade.html";
    }

    // for test purposes
    @PostMapping("/mike")
    public String acceptData(@RequestBody String payloadBody, @RequestHeader HttpHeaders headers)  {
        // Con este metodo podemos ver que paramentros enviamos con el post desde el form
        System.out.println("\nParametros recibidos: "+payloadBody+"\n");
        return "home";
    }

    // for test purposes
    @GetMapping("/petar")
    public ModelAndView forzar500(Model model) {
        // metodo para forzar un error 500 y ver la pagina de error
        Integer a;
        a = 1/0;
        return new ModelAndView();
    }

}
