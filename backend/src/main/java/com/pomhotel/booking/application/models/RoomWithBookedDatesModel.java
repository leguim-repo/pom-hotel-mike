package com.pomhotel.booking.application.models;

import java.sql.Date;
import java.util.List;

public class RoomWithBookedDatesModel {
    public RoomsModel room;
    public List<Date> bookedDates;

    public RoomWithBookedDatesModel() {
    }


    public RoomsModel getRoom() {
        return room;
    }

    public void setRoom(RoomsModel room) {
        this.room = room;
    }

    public List<Date> getBookedDates() {
        return bookedDates;
    }

    public void setBookedDates(List<Date> bookedDates) {
        this.bookedDates = bookedDates;
    }
}
