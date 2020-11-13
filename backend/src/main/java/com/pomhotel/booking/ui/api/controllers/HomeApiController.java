package com.pomhotel.booking.ui.api.controllers;


import com.pomhotel.booking.application.models.RoomWithBookedDatesModel;
import com.pomhotel.booking.application.models.RoomsModel;
import com.pomhotel.booking.application.services.RoomsAndBookedDatesService;
import com.pomhotel.booking.application.services.RoomsService;
import com.pomhotel.booking.ui.api.exceptions.RoomNotFoundException;
import org.apache.commons.logging.LogFactory;
import toDelete.sandbox.mvc.dto.SearchDTO;
import com.pomhotel.booking.ui.api.services.RandomMusicURLService;
import com.pomhotel.booking.ui.api.services.RandomMusicURLServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    // OK Endpoint of button [Find Rooms] in home and [Find Room] in rooms
    @PostMapping("/api/findroom")
    public List<RoomsModel> findRoomByFilterApi(@RequestBody SearchDTO dto) {
        Logger.info("findroom: " + dto.toString());

        if ( ( dto.minprice==null) && (dto.maxprice==null) && (dto.type==null) ) {
            dto.minprice = "1";
            dto.maxprice = "1000";
            dto.type = "0";
            dto.guests = "1";
        }
        List<RoomsModel> rooms = roomsService.findApplyingFilter(Integer.parseInt(dto.guests),Integer.parseInt(dto.minprice),Integer.parseInt(dto.maxprice), Long.parseLong(dto.type));

        return rooms;
    }

    // Ok Single item...really without use. Only for tests
    @GetMapping(value = "/api/roomdetail/{targetId}", produces = "application/json")
    //public ResponseEntity<RoomsModel> findRoomByIdApi(@PathVariable String targetId) {
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

