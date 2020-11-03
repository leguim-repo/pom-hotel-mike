package com.pomhotel.booking.ui.api.services;

import com.pomhotel.booking.application.models.RoomsModel;
import com.pomhotel.booking.application.services.RoomsService;
import com.pomhotel.booking.application.services.RoomsServiceImplementation;
import com.pomhotel.booking.ui.api.dto.BookingApiDTO;
import com.pomhotel.booking.ui.api.dto.CalculatedBookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

@Service
public class BusinessLogicApiServiceImplementation implements BusinessLogicApiService {
    RoomsService roomsService;
    DateTimeFormatter formatoDeEntrada = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    DateTimeFormatter formatoDeSalida = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Autowired
    public BusinessLogicApiServiceImplementation(RoomsService roomsService) {
        this.roomsService = roomsService;
    }

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
        RoomsModel room;
        double basePrice=0;
        System.out.println("bookCalculation *******");
        System.out.println("book: "+book.toString());

        room = roomsService.findById(book.roomId);

        calculatedBook.setRoomPricePerNight(room.pricePerNight);
        basePrice= this.calculateTotalPrice(Date.valueOf(book.checkIn),Date.valueOf(book.checkOut),room.pricePerNight);

        calculatedBook.setTotalNights(this.getDaysBetweenTwoDates(Date.valueOf(book.checkIn),Date.valueOf(book.checkOut)));

        if ( book.breakfastService ) {
            calculatedBook.setBreakFastPricePerNight(6);
            calculatedBook.setBreakFastTotalPrice(calculatedBook.breakFastPricePerNight*calculatedBook.totalNights);
            basePrice += calculatedBook.breakFastTotalPrice;
        }
        if ( book.carParkingService ) { basePrice += 20; }
        if ( book.spaService ) { basePrice += 30; }
        if ( book.laundryService ) { basePrice += 40; }
        if ( book.shuttleService ) { basePrice += 50; }

        calculatedBook.setTotalPrice(basePrice);

        System.out.println("calculatedBook: "+calculatedBook.toString());
        return calculatedBook;
    }

}
