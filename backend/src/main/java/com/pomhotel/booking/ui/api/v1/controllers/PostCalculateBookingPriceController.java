package com.pomhotel.booking.ui.api.v1.controllers;

import com.pomhotel.booking.application.services.BusinessLogicApiService;
import com.pomhotel.booking.ui.api.v1.dto.BookingApiDTO;
import com.pomhotel.booking.ui.api.v1.dto.CalculatedBookDTO;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "http://pom-hotel.code:3000", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class PostCalculateBookingPriceController {
    private static final org.apache.commons.logging.Log Logger = LogFactory.getLog("PostCalculateBookingPriceController.class");
    BusinessLogicApiService businessLogicService;

    public PostCalculateBookingPriceController(BusinessLogicApiService businessLogicService) {
        this.businessLogicService = businessLogicService;
    }

    //@PostMapping("/api/calculatebook")
    @PostMapping("calculatepriceofbook")
    public CalculatedBookDTO calculatePriceOfBook(@RequestBody @Valid BookingApiDTO booking) {
        CalculatedBookDTO bookingCalculado;
        //llamamos al servicio de calculadora para que devuelva el precio de la reserva
        Logger.info("recibido: "+booking);
        bookingCalculado = businessLogicService.callToCalculateBooking(booking);
        Logger.info("calculado y enviado: "+bookingCalculado);
        return bookingCalculado;
    }
}
