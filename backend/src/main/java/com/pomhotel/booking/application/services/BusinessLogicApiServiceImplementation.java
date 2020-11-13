package com.pomhotel.booking.application.services;

import com.pomhotel.booking.application.domain.factories.BookingsFactory;
import com.pomhotel.booking.application.models.BookingsModel;
import com.pomhotel.booking.application.models.RoomsModel;
import com.pomhotel.booking.application.models.RoomtypesModel;
import com.pomhotel.booking.ui.api.dto.BookingApiDTO;
import com.pomhotel.booking.ui.api.dto.CalculatedBookDTO;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
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


    //--- Auxiliar Functions ----------------------------------------------------
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
    public CalculatedBookDTO callToCalculateBooking(BookingApiDTO book) {
        return calculateTotalPriceBooking(book);
    }

    @Override
    public CalculatedBookDTO callToCalculateBooking(BookingsModel book) {
        return calculateTotalPriceBooking(bookingsFactory.createDTO(book));
    }



    //--- Methods of Our Business Logical
    @Override
    public double calculateBasePrice(long totalNights, double pricePerNight, Date checkIn) {
        // TODO realizar descuento al total por larga estancia. CheckIn nos marca la temporada
        return totalNights * pricePerNight;
    }

    @Override
    public double calculateBreakFastService(long totalNights, double pricePerNight) {
        return totalNights * pricePerNight;
    }

    @Override
    public double calculateCarParkingService(long totalNights, double pricePerNight) {
        return totalNights * pricePerNight;
    }

    @Override
    public double calculateSpaService(long totalNights, double pricePerNight, RoomtypesModel roomType) {
        // TODO depende del tipo de room va incluido

        return totalNights * pricePerNight;
    }

    @Override
    public double calculateLaundryService(long totalNights, double pricePerNight) {
        // TODO debe depender de las noches
        return totalNights * pricePerNight;
    }

    @Override
    public double calculateShuttleService(double servicePrice) {
        return servicePrice;
    }

    @Override
    public double calculateCodeDiscount(String code) {
        double discount = 0;
        switch (code) {
            case "CODE05":
                discount=-5;
                break;
            case "CODE10":
                discount=-10;
                break;
            case "CODE15":
                discount=-15;
                break;
            case "CODE20":
                discount=-20;
                break;
            case "CODE25":
                discount=-25;
                break;
            case "CODE50":
                discount=-50;
                break;
        }
        return discount;
    }

    @Override
    public CalculatedBookDTO calculateTotalPriceBooking(BookingApiDTO book) {
        CalculatedBookDTO calculatedBook = new CalculatedBookDTO();
        RoomsModel room;
        double basePrice=0;
        Logger.info("bookCalculation *******");
        Logger.info("book: "+book.toString());

        room = roomsService.findById(book.roomId);

        calculatedBook.setRoomPricePerNight(room.pricePerNight);
        calculatedBook.setTotalNights(this.getDaysBetweenTwoDates(Date.valueOf(book.checkIn),Date.valueOf(book.checkOut)));

        basePrice = calculateBasePrice(calculatedBook.totalNights, room.pricePerNight, Date.valueOf(book.checkIn));
        calculatedBook.setRoomTotalPrice(basePrice);


        if ( book.breakfastService ) {
            calculatedBook.setBreakFastPricePerNight(4);
            calculatedBook.setBreakFastTotalPrice(calculateBreakFastService(calculatedBook.totalNights,calculatedBook.breakFastPricePerNight));
            basePrice += calculatedBook.breakFastTotalPrice;
        }
        if ( book.carParkingService ) {
            calculatedBook.setCarParkingPricePerNight(10);
            calculatedBook.setCarParkingTotalPrice(calculateCarParkingService(calculatedBook.totalNights,calculatedBook.carParkingPricePerNight));
            basePrice += calculatedBook.carParkingTotalPrice;
        }

        if ( book.spaService ) {
            calculatedBook.setSpaPricePerNight(5);
            calculatedBook.setSpaTotalPrice(calculateSpaService(calculatedBook.totalNights, calculatedBook.spaPricePerNight, room.roomtypesByFkRoomtypeId));
            basePrice += calculatedBook.spaTotalPrice;
        }

        if ( book.laundryService ) {
            calculatedBook.setLaundryPricePerNight(50);
            calculatedBook.setLaundryTotalPrice(calculateLaundryService(calculatedBook.totalNights, calculatedBook.laundryPricePerNight));
            basePrice += calculatedBook.laundryTotalPrice;
        }

        if ( book.shuttleService ) {
            if (calculatedBook.totalNights >= 1){
                calculatedBook.setShuttlePricePerNight(20);
                calculatedBook.setShuttleTotalPrice(calculateShuttleService(calculatedBook.shuttlePricePerNight));
                basePrice += calculatedBook.shuttleTotalPrice;
            }
        }

        calculatedBook.setCodeDiscountPrice(calculateCodeDiscount(book.codeDiscount));
        basePrice +=calculateCodeDiscount(book.codeDiscount);
        calculatedBook.setTotalBookingPrice(basePrice);
        Logger.info("calculatedBook: "+calculatedBook.toString());
        return calculatedBook;
    }


    @Override
    public String dummyFunction() {
        return null;
    }


}
