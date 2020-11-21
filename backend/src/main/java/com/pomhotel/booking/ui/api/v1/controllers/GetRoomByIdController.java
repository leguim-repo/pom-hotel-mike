package com.pomhotel.booking.ui.api.v1.controllers;

import com.pomhotel.booking.application.models.RoomsModel;
import com.pomhotel.booking.application.services.RoomsService;
import com.pomhotel.booking.ui.api.v1.exceptions.RoomNotFoundException;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://pom-hotel.code:3000", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class GetRoomByIdController {
    private static final org.apache.commons.logging.Log Logger = LogFactory.getLog("GetRoomByIdController.class");
    RoomsService roomsService;

    public GetRoomByIdController(RoomsService roomsService) {
        this.roomsService = roomsService;
    }
    //@GetMapping("/api/roomdetail/{targetId}")
    @GetMapping("getroombyid/{targetId}")
    public RoomsModel getRoomById(@PathVariable String targetId) {
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
