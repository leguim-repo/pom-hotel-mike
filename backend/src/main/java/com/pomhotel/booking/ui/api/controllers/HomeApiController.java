package com.pomhotel.booking.ui.api.controllers;


import com.pomhotel.booking.application.models.RoomWithBookedDatesModel;
import com.pomhotel.booking.application.models.RoomsModel;
import com.pomhotel.booking.application.services.RoomsAndBookedDatesService;
import com.pomhotel.booking.application.services.RoomsService;
import com.pomhotel.booking.ui.api.exceptions.RoomNotFoundException;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//refererence: https://spring.io/guides/tutorials/rest/
@CrossOrigin(origins = "http://pom-hotel.code:3000", maxAge = 3600)
@RestController
public class HomeApiController {
    private static final org.apache.commons.logging.Log Logger = LogFactory.getLog("HomeApiController.class");
    RoomsService roomsService;
    RoomsAndBookedDatesService roomsAndBookedDatesService;

    @Autowired
    public HomeApiController(RoomsService roomsService, RoomsAndBookedDatesService roomsAndBookedDatesService) {
        this.roomsService = roomsService;
        this.roomsAndBookedDatesService = roomsAndBookedDatesService;
    }

    public HomeApiController(RoomsService roomsService) {
        this.roomsService = roomsService;
    }



    // OK Endpoint of link [Rooms]
    @GetMapping("/api/rooms")
    public List<RoomsModel> getAllRoomsApi() {
        List<RoomsModel> rooms = roomsService.findAll();
        return rooms;
    }

    @GetMapping("/api/roomsandbookeddates")
    public List<RoomWithBookedDatesModel> getAllRoomsWithBookedDates() {
        List<RoomWithBookedDatesModel> rooms = roomsAndBookedDatesService.findAllRoomsAndBookedDates();
        return rooms;
    }


    // Ok Single item...really without use. Only for tests
    //@GetMapping(value = "/api/roomdetail/{targetId}", produces = "application/json")
    //public ResponseEntity<RoomsModel> findRoomByIdApi(@PathVariable String targetId) {
    @GetMapping("/api/roomdetail/{targetId}")
    public RoomsModel findRoomByIdApi(@PathVariable String targetId) {
        RoomsModel requestedRoom=new RoomsModel();
        try {
            requestedRoom = roomsService.findById(Long.parseLong(targetId));
            Logger.debug(requestedRoom.getCode());
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RoomNotFoundException(targetId);

        }
        //return new ResponseEntity(requestedRoom,HttpStatus.OK);
        return requestedRoom;
    }



}

