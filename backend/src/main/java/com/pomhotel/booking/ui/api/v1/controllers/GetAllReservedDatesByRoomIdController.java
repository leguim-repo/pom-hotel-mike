package com.pomhotel.booking.ui.api.v1.controllers;

import com.pomhotel.booking.application.models.BookingDatesModel;
import com.pomhotel.booking.application.services.BookingsService;
import com.pomhotel.booking.application.services.BusinessLogicApiService;
import com.pomhotel.booking.ui.api.v1.exceptions.ApiManagerException;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://pom-hotel.code:3000", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class GetAllReservedDatesByRoomIdController {
    private static final org.apache.commons.logging.Log Logger = LogFactory.getLog("GetAllReservedDatesByRoomIdController.class");

    //--- Services & Variables used ---------------------------------------
    BookingsService bookingsService;


    //--- Constructor --------------------------------------------------
    @Autowired
    public GetAllReservedDatesByRoomIdController(BookingsService bookingsService) {
        this.bookingsService = bookingsService;

    }

    //@GetMapping("/api/dates/{targetId}")
    @GetMapping("getallreserveddatesbyroomid/{targetId}")
    public List<Date> getAllReservedDatesByRoomIdApi(@PathVariable String targetId) {
        List<Date> bookedDates = new ArrayList<>();
        try {
            List<BookingDatesModel> listBooked = bookingsService.getBookedDatesByRoomId(Long.parseLong(targetId));
            bookedDates = bookingsService.generateBookedDatesInRunTime(listBooked);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw ApiManagerException.NotFoundGetAllReservedDatesByRoomIdApi(e, targetId);
        }
        return bookedDates;
    }
}
