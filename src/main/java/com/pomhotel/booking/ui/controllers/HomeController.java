package com.pomhotel.booking.ui.controllers;

import com.pomhotel.booking.application.models.RoomsModel;
import com.pomhotel.booking.application.models.RoomtypesModel;
import com.pomhotel.booking.application.services.LoginService;
import com.pomhotel.booking.application.services.RoomTypesService;
import com.pomhotel.booking.application.services.RoomsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {
    LoginService loginService;
    RoomsService roomsService;
    RoomTypesService roomTypesService;
    @Autowired
    public HomeController(LoginService loginService, RoomsService roomsService, RoomTypesService roomTypesService) {
        this.loginService = loginService;
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
        //model.addAttribute("imgNav", "hotel-30.jpg");
        model.addAttribute("imgNav", "revato-10251-13112723-111323.jpg");

        List<RoomsModel> rooms = roomsService.findAll();
        model.addAttribute("rooms", rooms);

        List<RoomtypesModel> types = roomTypesService.findAll();
        model.addAttribute("types", types);

        return "listrooms";
    }

    @PostMapping("/rooms")
    public String roomsList() {
        return "redirect:/rooms";
    }

    // BOOk FLOW created with https://www.youtube.com/watch?v=GJkuTx1DQzg
    @RequestMapping("/bookroomnow")
    public String bookroomnow() {
        // aqui presentamos el formulario final para hacer la reserva

        return "booknow";
    }

    @PostMapping("/finalbooking")
    public String finalBooking(){
        String view="fail";
        //aqui recogemos los datos del formulario booknow
        // y finalemente introducimos los datos de reserva en la db
        // el servicio de turno nos debera devolver si se ha podido meter la reserva en la db con exito o si ha fallado
        if ( true ) {
            view="bookedsucces";
        }
        else {
            view="bookedfail";
        }
        return view;
    }

    //SIGN IN PAGE
    @GetMapping("/signin")
    public String signIn(Model model){
        return "signin";
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
