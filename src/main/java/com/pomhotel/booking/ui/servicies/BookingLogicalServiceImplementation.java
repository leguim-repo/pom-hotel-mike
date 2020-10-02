package com.pomhotel.booking.ui.servicies;

import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

//--- Service ----------------------------------------------------------
@Service
public class BookingLogicalServiceImplementation implements BookingLogicalService {
    DateTimeFormatter formatoDeEntrada = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    DateTimeFormatter formatoDeSalida = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    //--- Functions ----------------------------------------------------
    @Override
    public double calculateTotalPrice(Date checkIn, Date checkOut, double pricePerNight) {
        long nights = getDaysBetweenTwoDates(checkIn, checkOut);
        double totalPrice = nights * pricePerNight;
        return totalPrice;
    }

    @Override
    public Date stringToDate(String date) {
        return Date.valueOf( LocalDate.parse(date, formatoDeEntrada).format(formatoDeSalida) );
    }

    @Override
    public long getDaysBetweenTwoDates(Date date1, Date date2) {
        return TimeUnit.DAYS.convert((date2.getTime() - date1.getTime()), TimeUnit.MILLISECONDS);
    }

}
