package com.pomhotel.booking.ui.api;


import com.pomhotel.booking.application.models.RoomsModel;
import com.pomhotel.booking.application.models.RoomtypesModel;
import com.pomhotel.booking.application.services.RoomsService;
import com.pomhotel.booking.ui.controllers.SecurityController;
import com.pomhotel.booking.ui.dto.NewBookingDTO;
import com.pomhotel.booking.ui.dto.NewClientDTO;
import com.pomhotel.booking.ui.dto.SearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

//refererence: https://spring.io/guides/tutorials/rest/

@RestController
public class HomeApiController {
    RoomsService roomsService;
    SecurityController securityController;


    @Autowired
    public HomeApiController(RoomsService roomsService, SecurityController securityController) {
        this.roomsService = roomsService;
        this.securityController = securityController;

    }


    // The new endpoint of Home
    @GetMapping("/api")
    String apiHome(HttpServletRequest request) {
        return "Este endpoint seguro que no es el que buscas. Te has dejado alguna cosa mas";
    }


    // OK Get all rooms ( Link Rooms in NavBar )
    @GetMapping("/api/rooms")
    List<RoomsModel> getAllRoomsApi() {
        List<RoomsModel> rooms = roomsService.findAll();
        return rooms;
    }


    // Endpoint to new client
    @PostMapping("/api/newclient")
    NewClientDTO createNewClient(@RequestBody NewClientDTO newClient) {
        System.out.println("newclient: " + newClient.toString());
        return newClient;
    }


    // OK Endpoint for search a room ( Button Find Rooms in Home and Rooms )
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
    @GetMapping("/api/rooms/{targetId}")
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

    // EasterEgg
    @RequestMapping(value = "/api/", method = RequestMethod.TRACE)
    String spaceCowboys() {

        return "SPACECOWBOYS";
    }
}

