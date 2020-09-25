package com.pomhotel.booking.ui.controllers;

import com.pomhotel.booking.application.models.ClientsModel;
import com.pomhotel.booking.application.models.LoginsModel;
import com.pomhotel.booking.application.services.ClientLoginService;
import com.pomhotel.booking.ui.dto.NewClientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class LoginController {
    ClientLoginService clientLoginService;

    @Autowired
    LoginController(ClientLoginService clientLoginService){
        this.clientLoginService = clientLoginService;
    }

    @RequestMapping("/showMyLoginPage")
    public String showHome()
    {
        return "signin";
    }

    //SIGN IN PAGE
    @GetMapping("/signin")
    public String signIn(WebRequest request, Model model){
        NewClientDTO newclient = new NewClientDTO();
        model.addAttribute("newclient", newclient);

        return "signin";
    }

    //REGISTER NEW CLIENT
    @PostMapping("/registernewclient")
    public String registerNewClient(@ModelAttribute("user") @Valid NewClientDTO newclient, HttpServletRequest request, Errors errors) {
        String view;

        ClientsModel clientsModel = new ClientsModel(newclient.name, newclient.lastname, newclient.email);
        LoginsModel loginsModel = new LoginsModel(newclient.username, newclient.password, clientsModel);
        clientLoginService.createNewLoginAndUser(loginsModel);

        if (true) {
            view = "redirect:/home";
        } else {
            view = "registerfail";
        }
        return view;
    }
}
