package com.pomhotel.booking.ui.controllers;

import com.pomhotel.booking.application.models.RoomsModel;
import com.pomhotel.booking.application.models.RoomtypesModel;
import com.pomhotel.booking.application.services.RoomTypesService;
import com.pomhotel.booking.application.services.RoomsService;
import com.pomhotel.booking.ui.dto.SearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.util.List;

//--- Controller ----------------------------------------------------------
@Controller
public class HomeController {

    //--- Services & Variables used ---------------------------------------
    RoomsService roomsService;
    RoomTypesService roomTypesService;

    //--- Constructor -----------------------------------------------------
    @Autowired
    public HomeController(RoomsService roomsService, RoomTypesService roomTypesService) {
        this.roomsService = roomsService;
        this.roomTypesService = roomTypesService;
    }

    //--- Home Mappings -----------------------------------------------------
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

    //--- Rooms Mappings -----------------------------------------------------
    @GetMapping("/rooms")
    public String roomsList(@CookieValue("Checkin") String checkin,@CookieValue("Checkout") String checkout , Model model){
        model.addAttribute("strNav", "Find your rest");
        model.addAttribute("imgNav", "revato-10251-13112723-111323.jpg");

        List<RoomsModel> rooms = roomsService.findAll();
        model.addAttribute("rooms", rooms);

        List<RoomtypesModel> types = roomTypesService.findAll();
        model.addAttribute("types", types);

        model.addAttribute("checkin", checkin);
        model.addAttribute("checkout", checkout);

        return "listrooms";
    }

    @PostMapping("/rooms")
    public String roomsList(@ModelAttribute("newSearch") @Valid SearchDTO dto, Model model) {
        model.addAttribute("strNav", "Find your rest");
        model.addAttribute("imgNav", "revato-10251-13112723-111323.jpg");

        if ( ( dto.minprice==null) && (dto.maxprice==null) && (dto.type==null) ) {
            dto.minprice = "1";
            dto.maxprice = "1000";
            dto.type = "0";
        }
        List<RoomsModel> rooms = roomsService.findApplyingFilter(Integer.parseInt(dto.guests),Integer.parseInt(dto.minprice),Integer.parseInt(dto.maxprice), Long.parseLong(dto.type));
        model.addAttribute("rooms", rooms);

        List<RoomtypesModel> types = roomTypesService.findAll();
        model.addAttribute("types", types);


        model.addAttribute("checkin", "\""+dto.checkin+"\"");
        model.addAttribute("checkout", "\""+dto.checkout+"\"");

        return "listrooms";
    }


    //--- About Us Mappings --------------------------------------------------
    @GetMapping("/aboutus")
    public String aboutus(Model model){
        model.addAttribute("strNav", "Our great crew");
        model.addAttribute("imgNav", "img_bg_1.jpg");
        return "aboutus";
    }

    //--- Test Purposes Mappings -----------------------------------------------------
    @PostMapping("/mike")
    public String acceptData(@RequestBody String payloadBody, @RequestHeader HttpHeaders headers)  {
        //Note: Con este metodo podemos ver que paramentros enviamos con el post desde el form
        System.out.println("\nParametros recibidos: "+payloadBody+"\n");
        return "home";
    }

    @GetMapping("/petar")
    public ModelAndView forzar500(Model model) {
        //Note: metodo para forzar un error 500 y ver la pagina de error
        Integer a;
        a = 1/0;
        return new ModelAndView();
    }

}
