package com.pomhotel.booking.ui.api;

import com.pomhotel.booking.ui.dto.NewClientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
public class LoginApiController {

    @Autowired
    public LoginApiController() {
    }

    // TODO Endpoint to login
    @GetMapping("/api/signin")
    String apiSignIn(WebRequest request) {
        //CsrfToken token = (CsrfToken)request.getAttribute("username",CsrfToken.class.getName());
        //System.out.println("token: "+token);
        System.out.println("request: "+request);

        return "Aqui react tiene que pintar el login";
    }

    // TODO Endpoint to register a new client
    @PostMapping("/api/newclient")
    NewClientDTO createNewClient(@RequestBody NewClientDTO newClient) {
        System.out.println("newclient: " + newClient.toString());
        return newClient;
    }
}