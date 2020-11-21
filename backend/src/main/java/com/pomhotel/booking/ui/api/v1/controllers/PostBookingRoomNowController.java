package com.pomhotel.booking.ui.api.v1.controllers;

import com.pomhotel.booking.application.services.BookingsService;
import com.pomhotel.booking.ui.api.v1.dto.BookNowResponseDTO;
import com.pomhotel.booking.ui.api.v1.dto.BookingApiDTO;
import com.pomhotel.booking.ui.api.v1.exceptions.BookingApiException;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "http://pom-hotel.code:3000", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class PostBookingRoomNowController {
    private static final org.apache.commons.logging.Log Logger = LogFactory.getLog("PostBookingRoomNowController.class");

    BookingsService bookingsService;

    public PostBookingRoomNowController(BookingsService bookingsService) {
        this.bookingsService = bookingsService;
    }

    //@PostMapping("/api/bookroomnow")
    @PostMapping("bookingroomnow")
    public BookNowResponseDTO bookingRoomNow(@RequestBody @Valid BookingApiDTO dto) {
        BookNowResponseDTO response = new BookNowResponseDTO();
        Integer bookingId;

        try {
            bookingId=bookingsService.SaveNewBooking(dto);
            if (bookingId !=0) {
                response.bookNowResult=true;
                response.bookingId=bookingId;
                response.bookLink="/thankyou/"+bookingId;
            }
            else {
                response.bookNowResult=false;
                response.bookingId=0;
                response.bookLink="";
            }
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
