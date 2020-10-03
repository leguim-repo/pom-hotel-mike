package com.pomhotel.booking.ui.controllers;

import com.pomhotel.booking.application.models.BookingsModel;
import com.pomhotel.booking.application.models.RoomsModel;
import com.pomhotel.booking.application.services.BookingsService;
import com.pomhotel.booking.application.services.ClientLoginService;
import com.pomhotel.booking.application.services.RoomsService;
import com.pomhotel.booking.ui.dto.NewBookingDTO;
import com.pomhotel.booking.ui.servicies.BookingLogicalService;
import com.pomhotel.booking.ui.servicies.BookingLogicalServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
=======
import org.springframework.web.bind.annotation.*;

>>>>>>> feature/faildates
import javax.validation.Valid;
import java.util.Map;

//--- Controller ----------------------------------------------------------
@Controller
public class BookRoomController {

    //--- Services & Variables used ---------------------------------------
    RoomsService roomsService;
    BookingsService bookingsService;
    ClientLoginService clientsService;
    SecurityController securityController;
    BookingLogicalService bookingLogicalService;
    RoomsModel roomSelected;

    //--- Constructor --------------------------------------------------
    @Autowired
    public BookRoomController(RoomsService roomsService, BookingsService bookingsService, ClientLoginService clientsService, SecurityController securityController, BookingLogicalService bookingLogicalService) {
        this.roomsService = roomsService;
        this.bookingsService = bookingsService;
        this.clientsService = clientsService;
        this.securityController = securityController;
        this.bookingLogicalService = bookingLogicalService;
    }

    //--- Mappings -----------------------------------------------------
    @GetMapping("/bookroomnow/{id}")
    public String bookroomnow(@PathVariable("id") long id, @CookieValue("Checkin") String checkin,@CookieValue("Checkout") String checkout, Model model) {
    //@GetMapping("/bookroomnow")
    //public String bookroomnow(@RequestParam Map<String, String> params, Model model) {
        //System.out.println(params.toString());
        BookingLogicalService calculadora = new BookingLogicalServiceImplementation();
        roomSelected = roomsService.findById(id);
        model.addAttribute("imgNav", "high-performance.jpg");

        NewBookingDTO newBookingDTO = new NewBookingDTO();
        newBookingDTO.room = roomSelected;
        newBookingDTO.checkIn = checkin;
        newBookingDTO.checkOut = checkout;

        newBookingDTO.totalPrice = (int) calculadora.calculateTotalPrice(calculadora.stringToDate(checkin),calculadora.stringToDate(checkout),roomSelected.pricePerNight);

        model.addAttribute("newBooking", newBookingDTO);

        return "booknow";
    }

    @PostMapping("/bookroomnow")
    public String bookroomnow(@ModelAttribute("newBooking") @Valid NewBookingDTO dto) {
        String view;
        BookingsModel model = new BookingsModel();
<<<<<<< HEAD
=======
        //Falta agregar funcionalidad en la vista (no aqui) para que cuando se cambien las fechas se cambie el precioTotal
        System.out.println(dto.toString());
>>>>>>> feature/faildates

        try {
            model.checkIn = bookingLogicalService.stringToDate(dto.checkIn);
            model.checkOut = bookingLogicalService.stringToDate(dto.checkOut);

            model.roomsByFKRoomId = roomSelected; //no se como pasar bien los datos que no se editan... a ser felices así
            model.clientsByFkClientId = clientsService.findClientByUsername(securityController.currentUsername()); //esto es una porqueria: ineficiente e inadecuado... aunque sea funcional -.-
            model.totalPrice = bookingLogicalService.calculateTotalPrice(model.checkIn, model.checkOut, roomSelected.pricePerNight);
            bookingsService.saveOrUpdate(model);

            view="redirect:/home?bookedok";
        }
        catch (Exception e) {
            e.printStackTrace();
            view="redirect:/home?bookedfail";
        }

        return view;
    }
}
