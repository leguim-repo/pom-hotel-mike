package com.pomhotel.booking.ui.api.v1.controllers;

import com.pomhotel.booking.application.models.RoomWithBookedDatesModel;
import com.pomhotel.booking.application.models.RoomsModel;
import com.pomhotel.booking.application.services.RoomsAndBookedDatesService;
import com.pomhotel.booking.application.services.RoomsService;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://pom-hotel.code:3000", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class GetAllRoomsWithReservedDatesController {
    private static final org.apache.commons.logging.Log Logger = LogFactory.getLog("GetAllRoomsWithReservedDatesController.class");
    RoomsAndBookedDatesService roomsAndBookedDatesService;

    public GetAllRoomsWithReservedDatesController(RoomsAndBookedDatesService roomsAndBookedDatesService) {
        this.roomsAndBookedDatesService = roomsAndBookedDatesService;
    }

    //@GetMapping("/api/roomsandbookeddates")
    @GetMapping("getallroomswithreserveddates")
    public List<RoomWithBookedDatesModel> getAllRoomsApi() {
        List<RoomWithBookedDatesModel> rooms = roomsAndBookedDatesService.findAllRoomsAndBookedDates();
        return rooms;
    }
}
