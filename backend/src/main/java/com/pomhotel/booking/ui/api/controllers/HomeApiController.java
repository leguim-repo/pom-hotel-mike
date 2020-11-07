package com.pomhotel.booking.ui.api.controllers;


import com.pomhotel.booking.application.models.RoomWithBookedDatesModel;
import com.pomhotel.booking.application.models.RoomsModel;
import com.pomhotel.booking.application.services.RoomsAndBookedDatesService;
import com.pomhotel.booking.application.services.RoomsService;
import com.pomhotel.booking.ui.api.RoomNotFoundException;
import com.pomhotel.booking.ui.mvc.dto.SearchDTO;
import com.pomhotel.booking.ui.services.RandomMusicURLService;
import com.pomhotel.booking.ui.services.RandomMusicURLServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//refererence: https://spring.io/guides/tutorials/rest/
@CrossOrigin(origins = "http://pom-hotel.code:3000", maxAge = 3600)
@RestController
public class HomeApiController {
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
        System.out.println("findroom: " + dto.toString());

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
    @GetMapping("/api/roomdetail/{targetId}")
    public RoomsModel findRoomByIdApi(@PathVariable Long targetId) {
        RoomsModel requestedRoom;
        try {
            requestedRoom = roomsService.findById(targetId);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RoomNotFoundException(targetId);

        }
        return requestedRoom;
    }


    @GetMapping(value = "/api/music")
    public String musicApi() {
        String link="";
        RandomMusicURLService music = new RandomMusicURLServiceImplementation();
        // un string a pelo no se convierte a JSON. Como es simple creo el JSON a mano
        link="{ \"link\":\""+music.getRandomMusicURL()+"\" }";
        return link;
    }

    // EasterEgg
    @RequestMapping(value = "/api/", method = RequestMethod.TRACE)
    public String spaceCowboys() {

        return "SPACECOWBOYS";
    }
}

