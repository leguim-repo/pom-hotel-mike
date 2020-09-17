package com.pomhotel.booking.ui.controllers;

import com.pomhotel.booking.application.domain.entities.RoomtypesEntity;
import com.pomhotel.booking.application.models.LoginsModel;
import com.pomhotel.booking.application.models.RoomtypesModel;
import com.pomhotel.booking.application.repositories.RoomtypesRepository;
import com.pomhotel.booking.application.services.LoginService;
import com.pomhotel.booking.application.services.RoomTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@Controller
public class HomeController {
    LoginService loginService;

    @Autowired
    public HomeController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("datetime", new Date());
        model.addAttribute("projectname", "Pom Hotel & SPA");

        model.addAttribute("mode", "Test");

        List<LoginsModel> models = loginService.findAll();
        return "index";
    }

    @GetMapping("/petar")
    public ModelAndView forzar500(Model model) {
        // metodo para forzar un error 500 y ver la pagina de error
        Integer a;
        a = 1/0;
        return new ModelAndView();
    }

    /*
    https://www.generateit.net/javascript-decompressor/
    https://onlineasciitools.com/convert-decimal-to-ascii
    https://beautifier.io/
    */
    @PostMapping("/reserve")
    public String acceptData(@RequestBody String payloadBody, @RequestHeader HttpHeaders headers)  {
        // Con este metodo podemos ver que paramentros enviamos con el post desde el form
        System.out.println("\nParametros recibidos: "+payloadBody+"\n");
        return "index";
    }


}
