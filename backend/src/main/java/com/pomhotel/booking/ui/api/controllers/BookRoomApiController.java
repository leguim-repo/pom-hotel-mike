package com.pomhotel.booking.ui.api.controllers;

import com.pomhotel.booking.application.models.BookingsModel;
import com.pomhotel.booking.application.models.RoomsModel;
import com.pomhotel.booking.application.services.BookingsService;
import com.pomhotel.booking.application.services.ClientLoginService;
import com.pomhotel.booking.application.services.RoomsService;
import com.pomhotel.booking.ui.api.BookRoomNowException;
import com.pomhotel.booking.ui.api.dto.BookingApiDTO;
import com.pomhotel.booking.ui.api.dto.CalculatedBookDTO;
import com.pomhotel.booking.ui.api.services.BusinessLogicApiService;
import com.pomhotel.booking.ui.mvc.dto.NewBookingDTO;
import com.pomhotel.booking.ui.services.BookingLogicalService;
import com.pomhotel.booking.ui.services.BookingLogicalServiceImplementation;
import org.springframework.beans.CachedIntrospectionResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

@CrossOrigin(origins = "http://pom-hotel.code:3000", maxAge = 3600)
@RestController
public class BookRoomApiController {
    //--- Services & Variables used ---------------------------------------
    RoomsService roomsService;
    BookingsService bookingsService;
    ClientLoginService clientsService;
    BusinessLogicApiService businessLogicService;
    RoomsModel roomSelected;

    //--- Constructor --------------------------------------------------
    @Autowired
    public BookRoomApiController(RoomsService roomsService, BookingsService bookingsService, ClientLoginService clientsService  , BusinessLogicApiService businessLogicService) {
        this.roomsService = roomsService;
        this.bookingsService = bookingsService;
        this.clientsService = clientsService;
        this.businessLogicService = businessLogicService;
    }

    //--- Mappings -----------------------------------------------------
    // Esto codig es de pruebas se puede eliminar
    // Este endpoint es la creacion de la reserva le paso a react todos los datos de la reserva y que le ponga el boton de Confirm Booking
    // TODO OK Endpoint of button [Book Now] Create a booking and paint button [Confirm Booking]
    /*
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
    */

    // Calculadora del precio del room en funcion de los servicios que pida el cliente
    @PostMapping("/api/calculatebook")
    public CalculatedBookDTO calculatePriceOfBook(@RequestBody @Valid BookingApiDTO booking) {
        CalculatedBookDTO bookingCalculado = new CalculatedBookDTO();
        //llamamos al servicio de calculadora para que devuelva el precio de la reserva
        System.out.println("recibido: "+booking);
        bookingCalculado = businessLogicService.bookCalculation(booking);
        System.out.println("calculado y enviado: "+bookingCalculado);
        return bookingCalculado;
    }




    // TODO OK Endpoint of button [Confirm Booking] save booking to DataBase and alert if correct or not
    // Valorar construir un NewBookingDTO mas ligero y sin tanto object
    @PostMapping("/api/bookroomnow")
    public String bookRoomNowApi(@RequestBody @Valid NewBookingDTO dto) {
        String view;
        BookingsModel model = new BookingsModel();

        try {
            model.checkIn = businessLogicService.stringToDate(dto.checkIn);
            model.checkOut = businessLogicService.stringToDate(dto.checkOut);

            model.roomsByFKRoomId = roomsService.findById(dto.roomId);
            
            // TODO que hacemos con la fucking seguridad para hacer un post de reserva
            // como obtenemos el username si no funciona el login
            // pido el login y el usuario en el mismo formulario de confirmacion de la reservar y lo busco onfly
            model.clientsByFkClientId = clientsService.findClientByUsername("demos");

            //TODO pasar a businessLogicService
            model.totalPrice = businessLogicService.calculateTotalPrice(model.checkIn, model.checkOut, model.roomsByFKRoomId.pricePerNight);
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
