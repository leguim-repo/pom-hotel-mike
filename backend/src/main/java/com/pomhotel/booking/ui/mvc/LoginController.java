package com.pomhotel.booking.ui.mvc;

import com.pomhotel.booking.application.models.ClientsModel;
import com.pomhotel.booking.application.models.LoginsModel;
import com.pomhotel.booking.application.services.ClientLoginService;
import com.pomhotel.booking.ui.mvc.dto.NewClientDTO;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

//--- Controller ----------------------------------------------------------
@Controller
public class LoginController {

    //--- Services & Variables used ---------------------------------------
    ClientLoginService clientLoginService;

    //--- Constructor -----------------------------------------------------
    @Autowired
    LoginController(ClientLoginService clientLoginService){
        this.clientLoginService = clientLoginService;
    }

    //--- Sign In & Log Out Mappings --------------------------------------
    @GetMapping("/mvc/signin")
    public String signIn(WebRequest request, Model model){
        NewClientDTO newclient = new NewClientDTO();
        model.addAttribute("newclient", newclient);

        return "signin";
    }

    /*
    @GetMapping(value="/mvc/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        return "redirect:/mvc/signin?logout";
    }
    */



    //--- Register New Client Mapping --------------------------------------
    @PostMapping("/mvc/registernewclient")
    public String registerNewClient(@ModelAttribute("newclient") @Valid NewClientDTO newclient, HttpServletRequest request, Errors errors) {
        ClientsModel clientModel = new ClientsModel(newclient.name, newclient.lastname, newclient.email);
        LoginsModel loginModel = new LoginsModel(newclient.username, newclient.password, clientModel);
        clientLoginService.createClientAndLogin(loginModel);

        if (true) {
            return "redirect:/mvc/home";
        } else {
            return "registerfail";
        }
    }

}
