package com.pomhotel.booking.ui.api.controllers;

import com.pomhotel.booking.application.models.BookingDatesModel;
import com.pomhotel.booking.application.models.BookingsModel;
import com.pomhotel.booking.application.models.RoomsModel;
import com.pomhotel.booking.application.services.BookingsService;
import com.pomhotel.booking.application.services.ClientLoginService;
import com.pomhotel.booking.application.services.RoomsService;
import com.pomhotel.booking.ui.api.dto.BookNowResponseDTO;
import com.pomhotel.booking.ui.api.dto.BookingWithPricesDTO;
import com.pomhotel.booking.ui.api.exceptions.BookingApiException;
import com.pomhotel.booking.ui.api.dto.BookingApiDTO;
import com.pomhotel.booking.ui.api.dto.CalculatedBookDTO;
import com.pomhotel.booking.application.services.BusinessLogicApiService;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://pom-hotel.code:3000", maxAge = 3600)
@RestController
public class BookRoomApiController {
    private static final org.apache.commons.logging.Log Logger = LogFactory.getLog("BookRoomApiController.class");

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
    public BookingWithPricesDTO getBookingsByIdApi(@PathVariable String targetId) {

        BookingWithPricesDTO response = new BookingWithPricesDTO();
        BookingsModel booking = new BookingsModel();
        CalculatedBookDTO prices = new CalculatedBookDTO();

        try {
            booking = bookingsService.findById(Long.parseLong(targetId));
            prices = businessLogicService.callToCalculateBooking(booking);
            response.setBook(booking);
            response.setPrices(prices);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @GetMapping("/api/dates/{targetId}")
    public List<Date> getAllBookingsDatesByRoomIdApi(@PathVariable String targetId) {
        List<Date> bookedDates = new ArrayList<>();
        try {
            List<BookingDatesModel> listBooked = bookingsService.getBookedDatesByRoomId(Long.parseLong(targetId));
            bookedDates = bookingsService.generateBookedDatesInRunTime(listBooked);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return bookedDates;
    }


    // Calculadora del precio del room en funcion de los servicios que pida el cliente
    @PostMapping("/api/calculatebook")
    public CalculatedBookDTO calculatePriceOfBook(@RequestBody @Valid BookingApiDTO booking) {
        CalculatedBookDTO bookingCalculado;
        //llamamos al servicio de calculadora para que devuelva el precio de la reserva
        Logger.info("recibido: "+booking);
        bookingCalculado = businessLogicService.callToCalculateBooking(booking);
        Logger.info("calculado y enviado: "+bookingCalculado);
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
            bookingCalculado = businessLogicService.callToCalculateBooking(dto);
            model.totalPrice = bookingCalculado.totalBookingPrice;
            Logger.info("bookroomnow.model: "+model.toString());
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

}
