package com.pomhotel.booking.ui.api.exceptions;

import com.pomhotel.booking.ui.api.dto.BookingApiDTO;

public class BookingApiException extends RuntimeException{
    public BookingApiException(BookingApiDTO dto) {
        super("BookingApiDTO Exception " + dto.toString());
    }

}
