package com.pomhotel.booking.ui.api;

import com.pomhotel.booking.ui.dto.NewClientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;


@RestController
public class LoginApiController {

    @Autowired
    public LoginApiController() {
    }

    // The new endpoint of signin
    @GetMapping("/api/signin")
    String apiSignIn() {

        return "Aqui react tiene que pintar el login";
    }
}
