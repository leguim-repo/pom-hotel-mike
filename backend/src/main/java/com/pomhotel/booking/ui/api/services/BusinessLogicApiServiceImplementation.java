package com.pomhotel.booking.ui.api.services;

import com.pomhotel.booking.application.domain.factories.BookingsFactory;
import com.pomhotel.booking.application.models.BookingsModel;
import com.pomhotel.booking.application.models.RoomsModel;
import com.pomhotel.booking.application.services.RoomsService;
import com.pomhotel.booking.application.services.RoomsServiceImplementation;
import com.pomhotel.booking.ui.api.dto.BookingApiDTO;
import com.pomhotel.booking.ui.api.dto.CalculatedBookDTO;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

@Service
public class BusinessLogicApiServiceImplementation implements BusinessLogicApiService {
    private static final org.apache.commons.logging.Log Logger = LogFactory.getLog("BusinessLogicApiServiceImplementation.class");

    RoomsService roomsService;
    BookingsFactory bookingsFactory;
    DateTimeFormatter formatoDeEntrada = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    DateTimeFormatter formatoDeSalida = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Autowired
    public BusinessLogicApiServiceImplementation(RoomsService roomsService,BookingsFactory bookingsFactory) {
        this.roomsService = roomsService;
        this.bookingsFactory = bookingsFactory;
    }

    public BusinessLogicApiServiceImplementation() {
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
    public CalculatedBookDTO calculateBooking(BookingApiDTO book) {
        return calculateBook(book);
    }

    @Override
    public CalculatedBookDTO calculateBooking(BookingsModel book) {
        return calculateBook(bookingsFactory.createDTO(book));
    }

    @Override
    public CalculatedBookDTO calculateBook(BookingApiDTO book) {
        CalculatedBookDTO calculatedBook = new CalculatedBookDTO();
        RoomsModel room;
        double basePrice=0;
        Logger.info("bookCalculation *******");
        Logger.info("book: "+book.toString());

        room = roomsService.findById(book.roomId);

        calculatedBook.setRoomPricePerNight(room.pricePerNight);
        calculatedBook.setTotalNights(this.getDaysBetweenTwoDates(Date.valueOf(book.checkIn),Date.valueOf(book.checkOut)));

        //TODO aqui el basePrice se debe calcular en funcion de la temporada que pendiente para hacer
        //Atencion el Checkin marca el precio de temporada
        basePrice= calculatedBook.totalNights * room.pricePerNight;
        calculatedBook.setRoomTotalPrice(basePrice);

        if ( book.breakfastService ) {
            calculatedBook.setBreakFastPricePerNight(4);
            calculatedBook.setBreakFastTotalPrice(calculatedBook.totalNights * calculatedBook.breakFastPricePerNight);
            basePrice += calculatedBook.breakFastTotalPrice;
        }
        if ( book.carParkingService ) {
            calculatedBook.setCarParkingPricePerNight(10);
            calculatedBook.setCarParkingTotalPrice(calculatedBook.totalNights * calculatedBook.carParkingPricePerNight);
            basePrice += calculatedBook.carParkingTotalPrice;
        }

        if ( book.spaService ) {
            // TODO depende del tipo de room va incluido
            calculatedBook.setSpaPricePerNight(5);
            calculatedBook.setSpaTotalPrice(calculatedBook.totalNights * calculatedBook.spaPricePerNight);
            basePrice += calculatedBook.spaTotalPrice;
        }

        if ( book.laundryService ) {
            // TODO debe depender de las noches
            calculatedBook.setLaundryPricePerNight(50);
            calculatedBook.setLaundryTotalPrice(calculatedBook.totalNights * calculatedBook.laundryPricePerNight);
            basePrice += calculatedBook.laundryTotalPrice;
        }

        if ( book.shuttleService ) {
            if (calculatedBook.totalNights >= 1){
                calculatedBook.setShuttlePricePerNight(20);
                calculatedBook.setShuttleTotalPrice(calculatedBook.shuttlePricePerNight);
                basePrice += calculatedBook.shuttleTotalPrice;
            }
        }

        // TODO realizar descuento al total por larga estancia

        calculatedBook.setCodeDiscountPrice(0);
        switch (book.codeDiscount) {
            case "CODE05":
                calculatedBook.setCodeDiscountPrice(-5);
                break;
            case "CODE10":
                calculatedBook.setCodeDiscountPrice(-10);
                break;
            case "CODE15":
                calculatedBook.setCodeDiscountPrice(-15);
                break;
        }
        basePrice +=calculatedBook.codeDiscountPrice;


        calculatedBook.setTotalBookingPrice(basePrice);

        Logger.info("calculatedBook: "+calculatedBook.toString());
        return calculatedBook;
    }


}
