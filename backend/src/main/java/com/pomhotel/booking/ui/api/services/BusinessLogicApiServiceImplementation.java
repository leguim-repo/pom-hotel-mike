package com.pomhotel.booking.ui.api.services;

import com.pomhotel.booking.ui.api.dto.BookingApiDTO;
import com.pomhotel.booking.ui.api.dto.CalculatedBookDTO;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

@Service
public class BusinessLogicApiServiceImplementation implements BusinessLogicApiService {
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

    @Override
    public CalculatedBookDTO bookCalculation(BookingApiDTO book) {
        CalculatedBookDTO calculatedBook = new CalculatedBookDTO();
        double basePrice=1000;
        System.out.printf("book: "+book);


        if ( book.laundryService ) {
            basePrice += 40;
        }

        if ( book.shuttleService ) {
            basePrice += 50;
        }

        calculatedBook.setTotalPrice(basePrice);
        return calculatedBook;
    }

}
