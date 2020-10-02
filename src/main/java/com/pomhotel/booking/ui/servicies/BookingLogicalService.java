package com.pomhotel.booking.ui.servicies;

import java.sql.Date;

public interface BookingLogicalService {
    long getDaysBetweenTwoDates(Date date1, Date date2);
    Date stringToDate(String date);
    double calculateTotalPrice(Date checkIn, Date CheckOut, double pricePerNight);
}
