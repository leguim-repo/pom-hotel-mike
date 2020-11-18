package com.pomhotel.booking.application.services;

import com.pomhotel.booking.application.domain.factories.BookingsFactory;
import com.pomhotel.booking.application.models.BookingsModel;
import com.pomhotel.booking.application.models.PricingAdditionalServicesModel;
import com.pomhotel.booking.application.models.RoomsModel;
import com.pomhotel.booking.application.models.RoomTypesModel;
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

    // price list of additional services should be a table in DB
    private PricingAdditionalServicesModel listOfPrices = new PricingAdditionalServicesModel();

    @Autowired
    public BusinessLogicApiServiceImplementation(RoomsService roomsService,BookingsFactory bookingsFactory) {
        this.roomsService = roomsService;
        this.bookingsFactory = bookingsFactory;
    }

    public BusinessLogicApiServiceImplementation() {
    }


    //--- Auxiliar Functions ----------------------------------------------------

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
    public double calculateBasePrice(long totalNights, double pricePerNight) {
        return totalNights * pricePerNight;
    }

    @Override
    public double calculateSpecialPrice(long totalNights, double pricePerNight) {
        // Feature of calc pricePerNight in function of season pending because I need intercept prices from DB before show in frontentd!!
        double price = 0;
        double basePrice =0;
        if ( (totalNights >= listOfPrices.numberOfNightsForLongStay) ) {
            basePrice = pricePerNight - (pricePerNight * listOfPrices.roomPercentDiscountInPricePerNightLongStay);
        }
        price = basePrice * totalNights;
        return price;
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
    public double calculateSpaService(long totalNights, double pricePerNight, RoomTypesModel roomType) {
        // depende del tipo de room va incluido. Suite (1) y Luxury (4) spa incluido
        double price = switch ((int) roomType.id) {
            case 1,4 -> 0;
            case 2, 3, 5 -> totalNights * pricePerNight;
            default -> totalNights * pricePerNight;
        };
        return price;
    }

    @Override
    public double calculateLaundryService(long totalNights, double pricePerNight) {
        double price = 0;
        price = totalNights * pricePerNight;
        return price;
    }

    @Override
    public double calculateShuttleService(double servicePrice) {
        return servicePrice;
    }

    @Override
    public double calculateCodeDiscount(String code) {
        double discount = switch (code) {
            case "CODE05" -> -5;
            case "CODE10" -> -10;
            case "CODE15" -> -15;
            case "CODE20" -> -20;
            case "CODE25" -> -25;
            case "CODE50" -> -50;
            default -> 0;
        };
        return discount;
    }

    @Override
    public CalculatedBookDTO calculateTotalPriceBooking(BookingApiDTO book) {
        CalculatedBookDTO calculatedBook = new CalculatedBookDTO();
        RoomsModel room;
        double accumulated = 0;
        double basePrice = 0;
        double specialPrice = 0;
        Logger.info("bookCalculation *******");
        Logger.info("book: "+book.toString());

        room = roomsService.findById(book.roomId);

        calculatedBook.setRoomPricePerNight(room.pricePerNight);
        calculatedBook.setTotalNights(this.getDaysBetweenTwoDates(Date.valueOf(book.checkIn),Date.valueOf(book.checkOut)));

        basePrice = calculateBasePrice(calculatedBook.totalNights, room.pricePerNight);
        calculatedBook.setRoomTotalPrice(basePrice);

        specialPrice = calculateSpecialPrice(calculatedBook.totalNights, room.pricePerNight);

        if (specialPrice != 0) {
            accumulated = specialPrice;
            calculatedBook.setRoomSpecialPricePerNight(room.pricePerNight - (room.pricePerNight * listOfPrices.roomPercentDiscountInPricePerNightLongStay));
            calculatedBook.setRoomSpecialTotalPrice(specialPrice);
            calculatedBook.setLongStay(true);
        }
        else {
            accumulated = basePrice;
        }


        // check si es larga estancia
        calculatedBook.setLongStay(calculatedBook.getTotalNights() >= listOfPrices.numberOfNightsForLongStay);

        if ( book.breakfastService ) {
            calculatedBook.setBreakFastPricePerNight(listOfPrices.breakFastPricePerNight);
            calculatedBook.setBreakFastTotalPrice(calculateBreakFastService(calculatedBook.totalNights,calculatedBook.breakFastPricePerNight));
            accumulated += calculatedBook.breakFastTotalPrice;
        }
        if ( book.carParkingService ) {
            calculatedBook.setCarParkingPricePerNight(listOfPrices.carParkingPricePerNight);
            calculatedBook.setCarParkingTotalPrice(calculateCarParkingService(calculatedBook.totalNights,calculatedBook.carParkingPricePerNight));
            accumulated += calculatedBook.carParkingTotalPrice;
        }

        if ( book.spaService ) {
            calculatedBook.setSpaPricePerNight(listOfPrices.spaPricePerNight);
            calculatedBook.setSpaTotalPrice(calculateSpaService(calculatedBook.totalNights, calculatedBook.spaPricePerNight, room.roomtypesByFkRoomtypeId));
            accumulated += calculatedBook.spaTotalPrice;
        }

        if ( book.laundryService ) {
            calculatedBook.setLaundryPricePerNight(listOfPrices.laundryPricePerNight);
            calculatedBook.setLaundryTotalPrice(calculateLaundryService(calculatedBook.totalNights, calculatedBook.laundryPricePerNight));
            accumulated += calculatedBook.laundryTotalPrice;
        }

        if ( book.shuttleService ) {
            if (calculatedBook.totalNights >= 1){
                calculatedBook.setShuttlePricePerNight(listOfPrices.shuttlePricePerNight);
                calculatedBook.setShuttleTotalPrice(calculateShuttleService(calculatedBook.shuttlePricePerNight));
                accumulated += calculatedBook.shuttleTotalPrice;
            }
        }


        calculatedBook.setCodeDiscountPrice(calculateCodeDiscount(book.codeDiscount));
        accumulated +=calculateCodeDiscount(book.codeDiscount);
        calculatedBook.setTotalBookingPrice(accumulated);
        Logger.info("calculatedBook: "+calculatedBook.toString());
        return calculatedBook;
    }


}
