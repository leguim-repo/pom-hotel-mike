package com.pomhotel.booking.ui.api.exceptions;

import toDelete.sandbox.mvc.dto.NewBookingDTO;

public class BookRoomNowException extends RuntimeException{

    public BookRoomNowException(NewBookingDTO dto) {
        super("BookRoomNow Excetpion " + dto.toString());
    }
}
