package com.pomhotel.booking.ui.api.exceptions;

import com.pomhotel.booking.ui.api.dto.BookingApiDTO;

public class BookRoomNowException extends RuntimeException{

    public BookRoomNowException(BookingApiDTO dto) {
        super("BookRoomNow Exception " + dto.toString());
    }
}
