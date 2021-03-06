package com.pomhotel.booking.ui.api.v1.controllers;

import org.apache.commons.logging.LogFactory;
import com.pomhotel.booking.ui.api.v1.dto.NewClientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

//TODO https://github.com/bezkoder/spring-boot-spring-security-jwt-authentication
//@CrossOrigin(origins = "http://pom-hotel.code:3000", maxAge = 3600, allowCredentials = "true")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RestController
public class LoginApiController {
    private static final org.apache.commons.logging.Log Logger = LogFactory.getLog("LoginApiController.class");

    @Autowired
    public LoginApiController() {
    }

    // TODO Endpoint to login
    @GetMapping("/api/signin")
    String apiSignIn(HttpServletRequest request) {

        Logger.info(request.toString());
        return "Aqui react tiene que pintar el login";
    }

    // TODO Endpoint to register a new client
    @PostMapping("/api/newclient")
    NewClientDTO createNewClient(@RequestBody NewClientDTO newClient) {
        Logger.info("newclient: " + newClient.toString());
        return newClient;
    }
}
