package com.pomhotel.booking.ui.controllers;

import com.pomhotel.booking.application.models.LoginsModel;
import com.pomhotel.booking.application.models.RoomsModel;
import com.pomhotel.booking.application.services.LoginService;
import com.pomhotel.booking.application.services.RoomsService;
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
    RoomsService roomsService;

    @Autowired
    public HomeController(LoginService loginService, RoomsService roomsService) {
        this.loginService = loginService;
        this.roomsService = roomsService;
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("datetime", new Date());
        model.addAttribute("projectname", "Pom Hotel & SPA");

        model.addAttribute("mode", "Test");

        List<LoginsModel> models = loginService.findAll();

        return "index";
    }

    //REGISTRATION FORM (get and post)

    //Enter at home page (provisional)
    @GetMapping("/home")
    public String home(Model model) {

        return "home";
    }

    @GetMapping("/registrationform")
    public String registration(Model model) {
        //model.addAttribute("userForm", new User());

        return "registrationform";
    }

    @PostMapping("/registrationform")
    //public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
    public String registration() {
        /*
        userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        userService.save(userForm);
        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());
         */

        return "redirect:/";
    }


    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }


    // TODO petarme authentificate login/resitration is the way
    // login entry point
    @PostMapping("/authentificate")
    public ModelAndView loginView(Model model) {
        // recogemos las credenciales de cliente y llamamos al servicio para que las compruebe
        String logincorrect = "logincorrect";
        String loginfail = "loginfail";
        String view = "";
        /*
         si el servicio valida la credenciales que...que hacemos...como decimos al cliente que esta logeado correctamente?
         si las credenciales no son validas le tiramos un pop up de que no son validas
         */

        if (true) {
            view = logincorrect;
        }
        else {
            view = loginfail;
        }
        return new ModelAndView(view);
    }

    //Rooms without applying pre-filters.
    @GetMapping("/rooms")
    public String roomsList(Model model){
        List<RoomsModel> models = roomsService.findAll();
        model.addAttribute("rooms", models);
        return "listrooms";
    }



    // prebooking entry point
    @PostMapping("/prebooking")
    public ModelAndView booknow(Model model) {
        /*
         si el cliente esta conectado pasa la vista de checkavail
         si el cliente no esta conectado pasamos a al vista de login
        */

        String loginview = "login";
        String checkavail = "checkavail";
        String view = "";
        if (true) {
            view = checkavail;
        }
        else {
            view = loginview;
        }
        return new ModelAndView(view);
    }

    @PostMapping("/booking")
    public ModelAndView checkdates(Model model){
        // comprobar login de cliente por seguridad
        return new ModelAndView("booked");
    }

    /* paginas tontas se tiran directo en static
    @GetMapping("/aboutuss")
    public String aboutusview() {
        // es necesario o...lo puedo tirar por static directo?
        return "acercade";
    }
     */

    /*
    https://www.generateit.net/javascript-decompressor/
    https://onlineasciitools.com/convert-decimal-to-ascii
    https://beautifier.io/
    */

    // for test purposes
    @PostMapping("/mike")
    public String acceptData(@RequestBody String payloadBody, @RequestHeader HttpHeaders headers)  {
        // Con este metodo podemos ver que paramentros enviamos con el post desde el form
        System.out.println("\nParametros recibidos: "+payloadBody+"\n");
        return "index";
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
