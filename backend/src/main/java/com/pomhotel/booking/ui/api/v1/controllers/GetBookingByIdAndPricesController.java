package com.pomhotel.booking.ui.api.v1.controllers;

import com.pomhotel.booking.application.models.BookingsModel;
import com.pomhotel.booking.application.services.BookingsService;
import com.pomhotel.booking.application.services.BusinessLogicApiService;
import com.pomhotel.booking.ui.api.v1.dto.BookingWithPricesDTO;
import com.pomhotel.booking.ui.api.v1.dto.CalculatedBookDTO;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://pom-hotel.code:3000", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class GetBookingByIdAndPricesController {

    private static final org.apache.commons.logging.Log Logger = LogFactory.getLog("GetBookingByIdAndPricesController.class");

    //--- Services & Variables used ---------------------------------------
    BookingsService bookingsService;
    BusinessLogicApiService businessLogicService;


    //--- Constructor --------------------------------------------------
    @Autowired
    public GetBookingByIdAndPricesController(BookingsService bookingsService, BusinessLogicApiService businessLogicService) {
        this.bookingsService = bookingsService;
        this.businessLogicService = businessLogicService;

    }

    //@GetMapping("/api/booking/{targetId}")
    @GetMapping("getbookingbyidandprices/{targetId}")
    public BookingWithPricesDTO getPriceOfBooking(@PathVariable String targetId) {
        BookingWithPricesDTO response = new BookingWithPricesDTO();
        BookingsModel booking;
        CalculatedBookDTO prices;

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
}
