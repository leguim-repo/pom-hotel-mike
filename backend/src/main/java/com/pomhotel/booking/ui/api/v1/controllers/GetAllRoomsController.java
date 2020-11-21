package com.pomhotel.booking.ui.api.v1.controllers;

import com.pomhotel.booking.application.models.RoomsModel;
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
public class GetAllRoomsController {
    private static final org.apache.commons.logging.Log Logger = LogFactory.getLog("GetAllRoomsController.class");
    RoomsService roomsService;

    public GetAllRoomsController(RoomsService roomsService) {
        this.roomsService = roomsService;
    }

    //@GetMapping("/api/rooms")
    @GetMapping("getallrooms")
    public List<RoomsModel> getAllRoomsApi() {
        List<RoomsModel> rooms = roomsService.findAll();
        return rooms;
    }
}
