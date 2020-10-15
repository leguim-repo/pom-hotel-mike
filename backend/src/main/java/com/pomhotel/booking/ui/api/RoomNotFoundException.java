package com.pomhotel.booking.ui.api;

public class RoomNotFoundException extends RuntimeException{

    public RoomNotFoundException(long id) {
        super("Could not find room id " + id);
    }
}
