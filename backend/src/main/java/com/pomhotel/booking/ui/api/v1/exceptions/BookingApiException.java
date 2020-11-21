package com.pomhotel.booking.ui.api.v1.exceptions;

import com.pomhotel.booking.ui.api.v1.dto.BookingApiDTO;

public class BookingApiException extends RuntimeException{
    public BookingApiException(BookingApiDTO dto) {
        super("BookingApiDTO Exception " + dto.toString());
    }

}
