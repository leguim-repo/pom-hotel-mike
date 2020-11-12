package com.pomhotel.booking.application.services;

import com.pomhotel.booking.application.models.BookingsModel;
import com.pomhotel.booking.application.models.RoomtypesModel;
import com.pomhotel.booking.application.services.RoomsService;
import com.pomhotel.booking.ui.api.dto.BookingApiDTO;
import com.pomhotel.booking.ui.api.dto.CalculatedBookDTO;

import java.sql.Date;

public interface BusinessLogicApiService {

    //--- Auxiliar Functions ----------------------------------------------------
    double calculateTotalPrice(Date checkIn, Date checkOut, double pricePerNight);
    Date stringToDate(String date);
    long getDaysBetweenTwoDates(Date date1, Date date2);

    CalculatedBookDTO callToCalculateBooking(BookingApiDTO book);
    CalculatedBookDTO callToCalculateBooking(BookingsModel book);

    //--- Methods of Our Business Logical
    double calculateBasePrice(long totalNights, double pricePerNight, Date checkIn);
    double calculateBreakFastService(long totalNights, double pricePerNight);
    double calculateCarParkingService(long totalNights, double pricePerNight);
    double calculateSpaService(long totalNights, double pricePerNight, RoomtypesModel roomType);
    double calculateLaundryService(long totalNights, double pricePerNight);
    double calculateShuttleService(long totalNights, double pricePerNight);
    double calculateCodeDiscount(String code);



    CalculatedBookDTO calculateTotalPriceBooking(BookingApiDTO book);

    String dummyFunction();
}
