package com.pomhotel.booking.ui.api.v1.exceptions;

import com.pomhotel.booking.ui.api.v1.dto.BookingApiDTO;

public class BookRoomNowException extends RuntimeException{

    public BookRoomNowException(BookingApiDTO dto) {
        super("BookRoomNow Exception " + dto.toString());
    }
}
