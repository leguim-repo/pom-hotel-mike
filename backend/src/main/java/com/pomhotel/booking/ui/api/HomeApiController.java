package com.pomhotel.booking.ui.api;


import com.pomhotel.booking.application.models.RoomsModel;
import com.pomhotel.booking.application.models.RoomtypesModel;
import com.pomhotel.booking.application.services.RoomsService;
import com.pomhotel.booking.ui.controllers.SecurityController;
import com.pomhotel.booking.ui.dto.NewBookingDTO;
import com.pomhotel.booking.ui.dto.NewClientDTO;
import com.pomhotel.booking.ui.dto.SearchDTO;
import com.pomhotel.booking.ui.servicies.RandomMusicURLService;
import com.pomhotel.booking.ui.servicies.RandomMusicURLServiceImplementation;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.swing.*;
import java.util.List;

//refererence: https://spring.io/guides/tutorials/rest/
@CrossOrigin(origins = "http://pom-hotel.code:3000", maxAge = 3600)
@RestController
public class HomeApiController {
    RoomsService roomsService;
    SecurityController securityController;

    @Autowired
    public HomeApiController(RoomsService roomsService, SecurityController securityController) {
        this.roomsService = roomsService;
        this.securityController = securityController;
    }


    // OK Endpoint of link [Rooms]
    @GetMapping("/api/rooms")
    List<RoomsModel> getAllRoomsApi() {
        List<RoomsModel> rooms = roomsService.findAll();
        return rooms;
    }


    // OK Endpoint of button [Find Rooms] in home and [Find Room] in rooms
    @PostMapping("/api/findroom")
    List<RoomsModel> findRoomByFilterApi(@RequestBody SearchDTO dto) {
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
    RoomsModel findRoomByIdApi(@PathVariable Long targetId) {
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
    String musicApi() {
        String link="";
        RandomMusicURLService music = new RandomMusicURLServiceImplementation();
        // un string a pelo no se convierte a JSON. Como es simple creo el JSON a mano
        link="{ \"link\":\""+music.getRandomMusicURL()+"\" }";
        return link;
    }

    // EasterEgg
    @RequestMapping(value = "/api/", method = RequestMethod.TRACE)
    String spaceCowboys() {

        return "SPACECOWBOYS";
    }
}

