package com.pomhotel.booking.ui.controllers;

import com.pomhotel.booking.ui.dto.NewClientDTO;
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
        System.out.println("newclient:"+newclient.toString());
        if (true) {
            view = "redirect:/home";
        } else {
            view = "registerfail";
        }
        return view;
    }
}
