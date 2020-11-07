package com.pomhotel.booking.application.services;

import com.pomhotel.booking.application.models.RoomWithBookedDatesModel;
import com.pomhotel.booking.application.models.RoomsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoomsAndBookedDatesServiceImplementation implements RoomsAndBookedDatesService {
    RoomsService roomsService;
    BookingsService bookingsService;

    @Autowired
    public RoomsAndBookedDatesServiceImplementation(RoomsService roomsService, BookingsService bookingsService) {
        this.roomsService = roomsService;
        this.bookingsService = bookingsService;
    }



    @Override
    public List<RoomWithBookedDatesModel> findAllRoomsAndBookedDates() {
        List<RoomWithBookedDatesModel> allRoomsAndBookedDates = new ArrayList<>();
        List<RoomsModel> allRooms = roomsService.findAll();

        for (RoomsModel r: allRooms) {
            RoomWithBookedDatesModel room = new RoomWithBookedDatesModel();
            List<Date> bookedDate;

            room.setRoom(r);
            bookedDate = bookingsService.generateBookedDatesInRunTime(bookingsService.getBookedDatesByRoomId(r.roomtypesByFkRoomtypeId.getId()));

            room.setBookedDates(bookedDate);

            allRoomsAndBookedDates.add(room);
        }
        return allRoomsAndBookedDates;
    }
}
