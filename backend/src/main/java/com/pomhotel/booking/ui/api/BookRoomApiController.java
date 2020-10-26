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
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.Objects;

@CrossOrigin(origins = "http://pom-hotel.code:3000", maxAge = 3600)
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
    // Este endpoint es la creacion de la reserva le paso a react todos los datos de la reserva y que le ponga el boton de Confirm Booking
    // TODO OK Endpoint of button [Book Now] Create a booking and paint button [Confirm Booking]
    @GetMapping("/api/bookroomnow/room")
    //public NewBookingDTO bookRoomNowByIdApi(@PathVariable("id") long id, @CookieValue("Checkin") String checkin, @CookieValue("Checkout") String checkout, Model model) {
    public NewBookingDTO bookRoomNowByIdApi(@RequestParam MultiValueMap<String, String> customQuery) {

        BookingLogicalService calculadora = new BookingLogicalServiceImplementation();
        NewBookingDTO newBookingDTO = new NewBookingDTO();

        //model.addAttribute("imgNav", "high-performance.jpg");
        roomSelected = roomsService.findById(Long.parseLong(Objects.requireNonNull(customQuery.getFirst("id"))));
        newBookingDTO.roomId = roomSelected.id;
        newBookingDTO.room = roomSelected;
        newBookingDTO.checkIn = customQuery.getFirst("checkin");
        newBookingDTO.checkOut = customQuery.getFirst("checkout");;
        newBookingDTO.guests = customQuery.getFirst("guests");
        newBookingDTO.totalPrice = (int) calculadora.calculateTotalPrice(calculadora.stringToDate(newBookingDTO.checkIn),calculadora.stringToDate(newBookingDTO.checkOut),roomSelected.pricePerNight);

        //model.addAttribute("newBooking", newBookingDTO);

        return newBookingDTO;
    }

    // TODO OK Endpoint of button [Confirm Booking] save booking to DataBase and alert if correct or not
    // Valorar construir un NewBookingDTO mas ligero y sin tanto object
    @PostMapping("/api/bookroomnow")
    public String bookRoomNowApi(@RequestBody @Valid NewBookingDTO dto) {
        String view;
        BookingsModel model = new BookingsModel();

        try {
            model.checkIn = bookingLogicalService.stringToDate(dto.checkIn);
            model.checkOut = bookingLogicalService.stringToDate(dto.checkOut);

            model.roomsByFKRoomId = roomsService.findById(dto.roomId);
            
            // TODO que hacemos con la fucking seguridad para hacer un post de reserva
            // como obtenemos el username si no funciona el login
            // pido el login y el usuario en el mismo formulario de confirmacion de la reservar y lo busco onfly
            model.clientsByFkClientId = clientsService.findClientByUsername(securityController.currentUsername());

            model.totalPrice = bookingLogicalService.calculateTotalPrice(model.checkIn, model.checkOut, model.roomsByFKRoomId.pricePerNight);
            System.out.println("bookroomnow.model: "+model.toString());

            //TODO grabacion en ddbb desahbilitada para las pruebas
            //bookingsService.saveOrUpdate(model);

            view="redirect:/home?bookedok";
            //throw new BookRoomNowException(dto);
        }
        catch (Exception e) {
            e.printStackTrace();
            view="redirect:/home?bookedfail";
            throw new BookRoomNowException(dto);
            // cuando peta d
        }

        return view; // react debe procesar la respuesta tanto si es ok como ko y ver si status es 200
    }

}
