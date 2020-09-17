package com.pomhotel.booking.ui.controllers;

import com.pomhotel.booking.application.models.RoomtypesModel;
import com.pomhotel.booking.application.repositories.RoomtypesRepository;
import com.pomhotel.booking.application.services.RoomTypesService;
import org.apache.catalina.connector.CoyoteInputStream;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.transform.stream.StreamSource;
import java.io.InputStream;
import java.io.StringReader;
import java.util.Date;
import java.util.List;

@Controller
public class HomeController {
    RoomTypesService roomTypesService;

    @Autowired
    public HomeController(RoomTypesService roomTypesService) {
        this.roomTypesService = roomTypesService;
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("datetime", new Date());
        model.addAttribute("projectname", "Pom Hotel & SPA");

        model.addAttribute("mode", "Test");

        roomTypesService.findAll();

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
