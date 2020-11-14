package com.pomhotel.booking.application.services;

import com.pomhotel.booking.application.models.RoomTypesModel;
import com.pomhotel.booking.application.models.RoomsModel;
import com.pomhotel.booking.ui.api.dto.BookingApiDTO;
import com.pomhotel.booking.ui.api.dto.CalculatedBookDTO;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Array;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@SpringBootTest
class BusinessLogicApiServiceTest {

    @Autowired
    private BusinessLogicApiServiceImplementation mockService;

    @Test
    @DisplayName("El Service no es Null")
    public void ShouldInnjectedBeanNeverShouldBeNull() throws Exception
    {
        assertThat(mockService).isNotNull();
    }

    @Test
    void getDaysBetweenTwoDates() throws Exception {
        assertEquals(2, mockService.getDaysBetweenTwoDates(Date.valueOf("2020-01-01"), Date.valueOf("2020-01-03")));
        assertEquals(20, mockService.getDaysBetweenTwoDates(Date.valueOf("2020-01-01"), Date.valueOf("2020-01-21")));
        assertEquals(4, mockService.getDaysBetweenTwoDates(Date.valueOf("2021-01-30"), Date.valueOf("2021-02-03")));

    }

    @Test
    void calculateBasePrice() throws Exception {
        assertEquals(1000,mockService.calculateBasePrice(10,100));
        assertEquals(1900,mockService.calculateBasePrice(19,100));
        assertEquals(1600,mockService.calculateBasePrice(20,100));
        assertEquals(1680,mockService.calculateBasePrice(21,100));

        assertEquals(320,mockService.calculateBasePrice(10,32));
        assertEquals(512,mockService.calculateBasePrice(20,32));

        assertEquals(4500,mockService.calculateBasePrice(10,450));
        assertEquals(7200,mockService.calculateBasePrice(20,450));

    }

    @Test
    void calculateBreakFastService() throws Exception {
        assertEquals(6,mockService.calculateBasePrice(2,3));
    }

    @Test
    void calculateCarParkingService() throws Exception {
        assertEquals(100,mockService.calculateBasePrice(10,10));

    }

    @Test
    void calculateSpaService() throws Exception {
        RoomTypesModel roomType = new RoomTypesModel();
        roomType.setId(1);
        assertEquals(0,mockService.calculateSpaService(10,10,roomType));
        roomType.setId(2);
        assertEquals(100,mockService.calculateSpaService(10,10,roomType));
        roomType.setId(3);
        assertEquals(100,mockService.calculateSpaService(10,10,roomType));
        roomType.setId(4);
        assertEquals(0,mockService.calculateSpaService(10,10,roomType));
        roomType.setId(5);
        assertEquals(100,mockService.calculateSpaService(10,10,roomType));
    }

    @Test
    void calculateLaundryService() throws Exception {
        assertEquals(0, mockService.calculateLaundryService(0,10));
        assertEquals(100, mockService.calculateLaundryService(10,10));

    }

    @Test
    void calculateShuttleService() throws Exception {
        assertEquals(10,mockService.calculateShuttleService(10));
    }

    @Test
    void calculateCodeDiscount() throws Exception {
        assertNotEquals(-10,mockService.calculateCodeDiscount("CODE101"));
        assertEquals( -5, mockService.calculateCodeDiscount("CODE05"));
        assertEquals( -10, mockService.calculateCodeDiscount("CODE10"));
        assertEquals( -15, mockService.calculateCodeDiscount("CODE15"));
        assertEquals( -20, mockService.calculateCodeDiscount("CODE20"));
        assertEquals( -25, mockService.calculateCodeDiscount("CODE25"));
        assertEquals( -50, mockService.calculateCodeDiscount("CODE50"));
        assertEquals( 0, mockService.calculateCodeDiscount("cadcada"));
        assertEquals( 0, mockService.calculateCodeDiscount("OWQIWO1232EX<"));

    }

    @Test
    //@Disabled("pending to finish")
    void calculateTotalPriceBooking() throws Exception {
        BusinessLogicApiServiceImplementation logicaMock = new BusinessLogicApiServiceImplementation();
        BookingApiDTO book = new BookingApiDTO();
        CalculatedBookDTO calculated = new CalculatedBookDTO();

        book.setRoomId(10);
        book.setCheckIn("2020-12-20");
        book.setCheckOut("2021-01-09");
        book.setGuests("5");
        book.setBreakfastService(true);
        book.setCarParkingService(true);
        book.setSpaService(true);
        book.setLaundryService(true);
        book.setShuttleService(true);
        book.setCodeDiscount("CODE50aa");

        calculated.setTotalNights(20);
        calculated.setRoomPricePerNight(160);
        calculated.setRoomTotalPrice(2650);
        calculated.setBreakFastPricePerNight(4);
        calculated.setBreakFastTotalPrice(80);
        calculated.setCarParkingPricePerNight(10);
        calculated.setCarParkingTotalPrice(200);
        calculated.setSpaPricePerNight(5);
        calculated.setSpaTotalPrice(100);
        calculated.setLaundryPricePerNight(2);
        calculated.setLaundryTotalPrice(40);
        calculated.setShuttlePricePerNight(20);
        calculated.setShuttleTotalPrice(20);
        calculated.setCodeDiscountPrice(0);
        calculated.setTotalBookingPrice(3000);

        when(logicaMock.calculateTotalPriceBooking(book)).thenReturn(calculated);
        assertThat(calculated).isNotNull();
    }

}