package com.pomhotel.booking.application.services;

import com.pomhotel.booking.application.models.BookingsModel;
import com.pomhotel.booking.application.models.RoomTypesModel;
import com.pomhotel.booking.ui.api.v1.dto.BookingApiDTO;
import com.pomhotel.booking.ui.api.v1.dto.CalculatedBookDTO;

import java.sql.Date;

public interface BusinessLogicApiService {

    //--- Auxiliar Functions ----------------------------------------------------
    long getDaysBetweenTwoDates(Date date1, Date date2);

    CalculatedBookDTO callToCalculateBooking(BookingApiDTO book);
    CalculatedBookDTO callToCalculateBooking(BookingsModel book);

    //--- Methods of Our Business Logical
    double calculateBasePrice(long totalNights, double pricePerNight);
    double calculateSpecialPrice(long totalNights, double pricePerNight);
    double calculateBreakFastService(long totalNights, double pricePerNight);
    double calculateCarParkingService(long totalNights, double pricePerNight);
    double calculateSpaService(long totalNights, double pricePerNight, RoomTypesModel roomType);
    double calculateLaundryService(long totalNights, double pricePerNight);
    double calculateShuttleService(double servicePrice);
    double calculateCodeDiscount(String code);

    //--- Core Method of Our Business Logical
    CalculatedBookDTO calculateTotalPriceBooking(BookingApiDTO book);

}
