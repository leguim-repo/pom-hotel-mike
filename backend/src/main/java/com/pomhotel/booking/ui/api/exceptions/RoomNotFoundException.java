package com.pomhotel.booking.ui.api.exceptions;

public class RoomNotFoundException extends RuntimeException{

    public RoomNotFoundException(long id) {
        super("Could not find room id " + id);
    }
}
