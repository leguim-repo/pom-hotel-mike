package com.pomhotel.booking.ui.api;

import com.pomhotel.booking.ui.dto.NewBookingDTO;

public class BookRoomNowException extends RuntimeException{

    public BookRoomNowException(NewBookingDTO dto) {
        super("BookRoomNow Excetpion " + dto.toString());
    }
}
