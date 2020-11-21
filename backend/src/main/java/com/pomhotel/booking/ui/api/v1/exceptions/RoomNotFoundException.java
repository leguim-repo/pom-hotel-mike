package com.pomhotel.booking.ui.api.v1.exceptions;

public class RoomNotFoundException extends RuntimeException{

    public RoomNotFoundException(long id) {
        super("Could not find room id " + id);
    }
    public RoomNotFoundException(String id) {
        super("Could not find room id " + id);
    }
}
