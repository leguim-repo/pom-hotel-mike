package com.pomhotel.booking.ui.api;


import com.pomhotel.booking.application.models.RoomsModel;
import com.pomhotel.booking.application.services.RoomsService;
import com.pomhotel.booking.ui.dto.NewBookingDTO;
import com.pomhotel.booking.ui.dto.NewClientDTO;
import com.pomhotel.booking.ui.dto.SearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//refererence: https://spring.io/guides/tutorials/rest/

@RestController
public class ApiProvider {
    RoomsService roomsService;

    @Autowired
    public ApiProvider(RoomsService roomsService) {
        this.roomsService = roomsService;
    }

    //
    @GetMapping("/api/")
    String dummyEndPoint() {
        return "Este endpoint seguro que no es el que buscas. Te has dejado alguna cosa mas";
    }

    // Get all rooms
    @GetMapping("/api/rooms")
    List<RoomsModel> getAllRoomsApi() {
        List<RoomsModel> rooms = roomsService.findAll();
        return rooms;
    }

    // Endpoint to make a reservation
    @PostMapping("/api/bookroomnow")
    NewBookingDTO bookRoomNow(@RequestBody NewBookingDTO newBooking) {
        System.out.println("bookRoomNow: " + newBooking.toString());
        return newBooking;
    }

    // Endpoint to new client
    @PostMapping("/api/newclient")
    NewClientDTO createNewClient(@RequestBody NewClientDTO newClient) {
        System.out.println("newclient: " + newClient.toString());
        return newClient;
    }

    // Endpoint for search a room
    @PostMapping("/api/newclient")
    SearchDTO searchRoom(@RequestBody SearchDTO targetRoom) {
        System.out.println("searchRoom: " + targetRoom.toString());
        return targetRoom;
    }

    // Single item...really only for tests
    @GetMapping("/api/rooms/{targetId}")
    RoomsModel getRoomById(@PathVariable Long targetId) {
        //TODO: proteger si el numero de id no existe (ej: id = 200)
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

