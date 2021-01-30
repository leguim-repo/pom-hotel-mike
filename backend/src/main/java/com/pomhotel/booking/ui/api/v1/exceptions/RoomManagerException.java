package com.pomhotel.booking.ui.api.v1.exceptions;

import java.io.Serializable;

public class RoomManagerException extends RuntimeException implements Serializable {
    private RoomManagerException(Exception e, String errorMessage) {
        super(errorMessage, e);
    }

    public static RoomManagerException RoomNotFoundById(Exception e, String id) {
        return new RoomManagerException(e,"RoomNotFoundById. Room id = " + id + " NOT FOUND");

    }
}


