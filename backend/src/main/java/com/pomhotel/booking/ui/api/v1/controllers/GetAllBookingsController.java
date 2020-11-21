package com.pomhotel.booking.ui.api.v1.controllers;

import com.pomhotel.booking.application.models.BookingsModel;
import com.pomhotel.booking.application.services.BookingsService;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://pom-hotel.code:3000", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class GetAllBookingsController {

    private static final org.apache.commons.logging.Log Logger = LogFactory.getLog("GetAllBookingsController.class");

    //--- Services & Variables used ---------------------------------------
    BookingsService bookingsService;

    //--- Constructor --------------------------------------------------
    @Autowired
    public GetAllBookingsController(BookingsService bookingsService) {
        this.bookingsService = bookingsService;
    }
    //@GetMapping("/api/bookings")
    @GetMapping("getallbookings")
    public List<BookingsModel> getAllBookingsApi() {
        Logger.info("getallbookings");
        List<BookingsModel> bookings = bookingsService.findAll();
        return bookings;
    }
}
