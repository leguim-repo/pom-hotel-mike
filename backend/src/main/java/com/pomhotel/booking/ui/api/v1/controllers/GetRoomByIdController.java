package com.pomhotel.booking.ui.api.v1.controllers;

import com.pomhotel.booking.application.models.RoomsModel;
import com.pomhotel.booking.application.services.RoomsService;
import com.pomhotel.booking.ui.api.v1.exceptions.RoomManagerException;
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
    @GetMapping("getroombyid/{targetId}")
    public RoomsModel getRoomById(@PathVariable String targetId) throws RoomManagerException {
        RoomsModel requestedRoom;
        try {
            requestedRoom = roomsService.findById(Long.parseLong(targetId));
            Logger.debug(requestedRoom.getCode());
        }
        catch (Exception e) {
            throw RoomManagerException.RoomNotFoundById(e, targetId);
        }
        return requestedRoom;
        /* I should be use the following way
        return new ResponseEntity(requestedRoom, HttpStatus.OK);
        */
    }
}

