package com.pomhotel.booking.ui.api.controllers;

import com.pomhotel.booking.application.models.BookingDatesModel;
import com.pomhotel.booking.application.models.BookingsModel;
import com.pomhotel.booking.application.models.RoomsModel;
import com.pomhotel.booking.application.services.BookingsService;
import com.pomhotel.booking.application.services.ClientLoginService;
import com.pomhotel.booking.application.services.RoomsService;
import com.pomhotel.booking.ui.api.dto.BookNowResponseDTO;
import com.pomhotel.booking.ui.api.exceptions.BookingApiException;
import com.pomhotel.booking.ui.api.dto.BookingApiDTO;
import com.pomhotel.booking.ui.api.dto.CalculatedBookDTO;
import com.pomhotel.booking.ui.api.services.BusinessLogicApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.util.List;

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

    @GetMapping("/api/bookings")
    public List<BookingsModel> getAllBookingsApi() {
        List<BookingsModel> bookings = bookingsService.findAll();
        return bookings;
    }

    @GetMapping("/api/booking/{targetId}")
    public BookingsModel getBookingsByIdApi(@PathVariable long targetId) {
        BookingsModel booking = new BookingsModel();
        CalculatedBookDTO bookingCalculado;
        //bookingCalculado = businessLogicService.bookCalculation(booking);

        try {
            booking = bookingsService.findById(targetId);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return booking;
    }

    @GetMapping("/api/dates/{targetId}")
    public List<Date> getAllBookingsDatesApi(@PathVariable long targetId) {
        List<BookingDatesModel> listBooked = bookingsService.getBookedDatesByRoomId(targetId);
        List<Date> bookedDates = bookingsService.generateBookedDatesInRunTime(listBooked);
        return bookedDates;
    }


    // Calculadora del precio del room en funcion de los servicios que pida el cliente
    @PostMapping("/api/calculatebook")
    public CalculatedBookDTO calculatePriceOfBook(@RequestBody @Valid BookingApiDTO booking) {
        CalculatedBookDTO bookingCalculado;
        //llamamos al servicio de calculadora para que devuelva el precio de la reserva
        System.out.println("recibido: "+booking);
        bookingCalculado = businessLogicService.bookCalculation(booking);
        System.out.println("calculado y enviado: "+bookingCalculado);
        return bookingCalculado;
    }




    // TODO OK Endpoint of button [Confirm Booking] save booking to DataBase and alert if correct or not
    // Valorar construir un NewBookingDTO mas ligero y sin tanto object
    @PostMapping("/api/bookroomnow")
    public BookNowResponseDTO bookRoomNowApi(@RequestBody @Valid BookingApiDTO dto) {
        BookNowResponseDTO response = new BookNowResponseDTO();
        CalculatedBookDTO bookingCalculado;
        RoomsModel room;
        Integer bookingId = 0;
        BookingsModel model = new BookingsModel();
        try {
            room = roomsService.findById(dto.roomId);
            model.checkIn = Date.valueOf(dto.checkIn);
            model.checkOut = Date.valueOf(dto.checkOut);
            model.guests = room.guests;
            model.clientsByFkClientId = clientsService.findClientByUsername("miguel"); // asignado a un cliente por defecto al no tener login
            model.breakfast = dto.breakfastService;
            model.carparking = dto.carParkingService;
            model.spa = dto.spaService;
            model.laundry = dto.laundryService;
            model.shuttle = dto.shuttleService;
            model.codediscount = dto.codeDiscount;
            model.clientEmail = dto.email;
            model.roomsByFKRoomId = room;
            bookingCalculado = businessLogicService.bookCalculation(dto);
            model.totalPrice = bookingCalculado.totalBookingPrice;
            System.out.println("bookroomnow.model: "+model.toString());

            //TODO grabacion en ddbb desahbilitada para las pruebas
            bookingId=bookingsService.save(model);


            response.bookNowResult=true;
            response.bookingId=bookingId;
            response.bookLink="/thankyou/"+bookingId;
        }
        catch (Exception e) {
            e.printStackTrace();
            response.bookNowResult=false;
            response.bookingId=0;
            response.bookLink="";
            throw new BookingApiException(dto);
        }

        return response;
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

}
