package com.pomhotel.booking.ui.api;

import com.pomhotel.booking.application.models.BookingsModel;
import com.pomhotel.booking.application.models.RoomsModel;
import com.pomhotel.booking.application.services.BookingsService;
import com.pomhotel.booking.application.services.ClientLoginService;
import com.pomhotel.booking.application.services.RoomsService;
import com.pomhotel.booking.ui.controllers.SecurityController;
import com.pomhotel.booking.ui.dto.NewBookingDTO;
import com.pomhotel.booking.ui.servicies.BookingLogicalService;
import com.pomhotel.booking.ui.servicies.BookingLogicalServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
public class BookRoomApiController {
    //--- Services & Variables used ---------------------------------------
    RoomsService roomsService;
    BookingsService bookingsService;
    ClientLoginService clientsService;
    SecurityController securityController;
    BookingLogicalService bookingLogicalService;
    RoomsModel roomSelected;

    //--- Constructor --------------------------------------------------
    @Autowired
    public BookRoomApiController(RoomsService roomsService, BookingsService bookingsService, ClientLoginService clientsService, SecurityController securityController, BookingLogicalService bookingLogicalService) {
        this.roomsService = roomsService;
        this.bookingsService = bookingsService;
        this.clientsService = clientsService;
        this.securityController = securityController;
        this.bookingLogicalService = bookingLogicalService;
    }

    //--- Mappings -----------------------------------------------------
    // para la creacion de la reserva le paso a react todos los datos de la reserva y que le ponga el boton de Confirm Booking
    @GetMapping("/api/bookroomnow/{id}")
    public String bookroomnow(@PathVariable("id") long id, @CookieValue("Checkin") String checkin, @CookieValue("Checkout") String checkout, Model model) {
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

    // OK Endpoint to confirm a booking ( Button BookNow )
    @PostMapping("/api/bookroomnow")
    public String bookRoomNowApi(@RequestBody @Valid NewBookingDTO dto) {
        String view;
        BookingsModel model = new BookingsModel();

        try {
            model.checkIn = bookingLogicalService.stringToDate(dto.checkIn);
            model.checkOut = bookingLogicalService.stringToDate(dto.checkOut);

            model.roomsByFKRoomId = roomsService.findById(dto.roomId);

            model.clientsByFkClientId = clientsService.findClientByUsername(securityController.currentUsername());

            model.totalPrice = bookingLogicalService.calculateTotalPrice(model.checkIn, model.checkOut, model.roomsByFKRoomId.pricePerNight);
            System.out.println("bookroomnow.model: "+model.toString());

            //TODO grabacion en ddbb desahbilitada para las pruebas
            //bookingsService.saveOrUpdate(model);

            view="redirect:/home?bookedok";
        }
        catch (Exception e) {
            e.printStackTrace();
            view="redirect:/home?bookedfail";
            throw new BookRoomNowException(dto);
        }

        return view;
    }

}
