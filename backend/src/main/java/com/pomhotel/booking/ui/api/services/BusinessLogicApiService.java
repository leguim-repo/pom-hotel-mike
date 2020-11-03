package com.pomhotel.booking.ui.api.services;

import com.pomhotel.booking.application.services.RoomsService;
import com.pomhotel.booking.ui.api.dto.BookingApiDTO;
import com.pomhotel.booking.ui.api.dto.CalculatedBookDTO;

import java.sql.Date;

public interface BusinessLogicApiService {

    //--- Functions ----------------------------------------------------
    double calculateTotalPrice(Date checkIn, Date checkOut, double pricePerNight);

    Date stringToDate(String date);

    long getDaysBetweenTwoDates(Date date1, Date date2);

    CalculatedBookDTO bookCalculation(BookingApiDTO book);
}
