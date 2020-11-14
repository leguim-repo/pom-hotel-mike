package com.pomhotel.booking.application.services;

import com.pomhotel.booking.application.models.RoomTypesModel;
import com.pomhotel.booking.application.models.RoomsModel;
import com.pomhotel.booking.ui.api.dto.BookingApiDTO;
import com.pomhotel.booking.ui.api.dto.CalculatedBookDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Date;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@SpringBootTest
class BusinessLogicApiServiceTest {
    @MockBean
    private RoomsService roomsService;

    @Autowired
    private BusinessLogicApiServiceImplementation businessService;

    @Test
    @DisplayName("El Service no es Null")
    public void ShouldInnjectedBeanNeverShouldBeNull() throws Exception
    {
        assertThat(businessService).isNotNull();
    }

    @Test
    void getDaysBetweenTwoDates() throws Exception {
        assertEquals(2, businessService.getDaysBetweenTwoDates(Date.valueOf("2020-01-01"), Date.valueOf("2020-01-03")));
        assertEquals(20, businessService.getDaysBetweenTwoDates(Date.valueOf("2020-01-01"), Date.valueOf("2020-01-21")));
        assertEquals(4, businessService.getDaysBetweenTwoDates(Date.valueOf("2021-01-30"), Date.valueOf("2021-02-03")));

    }

    @Test
    void calculateBasePrice() throws Exception {
        assertEquals(1000, businessService.calculateBasePrice(10,100));
        assertEquals(1900, businessService.calculateBasePrice(19,100));
        assertEquals(2000, businessService.calculateBasePrice(20,100));
        assertEquals(2100, businessService.calculateBasePrice(21,100));

        assertEquals(320, businessService.calculateBasePrice(10,32));
        assertEquals(640, businessService.calculateBasePrice(20,32));

        assertEquals(4500, businessService.calculateBasePrice(10,450));
        assertEquals(9000, businessService.calculateBasePrice(20,450));

    }

    @Test
    void calculateBreakFastService() throws Exception {
        assertEquals(6, businessService.calculateBasePrice(2,3));
    }

    @Test
    void calculateCarParkingService() throws Exception {
        assertEquals(100, businessService.calculateBasePrice(10,10));

    }

    @Test
    void calculateSpaService() throws Exception {
        RoomTypesModel roomType = new RoomTypesModel();
        roomType.setId(1);
        assertEquals(0, businessService.calculateSpaService(10,10,roomType));
        roomType.setId(2);
        assertEquals(100, businessService.calculateSpaService(10,10,roomType));
        roomType.setId(3);
        assertEquals(100, businessService.calculateSpaService(10,10,roomType));
        roomType.setId(4);
        assertEquals(0, businessService.calculateSpaService(10,10,roomType));
        roomType.setId(5);
        assertEquals(100, businessService.calculateSpaService(10,10,roomType));
    }

    @Test
    void calculateLaundryService() throws Exception {
        assertEquals(0, businessService.calculateLaundryService(0,10));
        assertEquals(100, businessService.calculateLaundryService(10,10));

    }

    @Test
    void calculateShuttleService() throws Exception {
        assertEquals(10, businessService.calculateShuttleService(10));
    }

    @Test
    void calculateCodeDiscount() throws Exception {
        assertNotEquals(-10, businessService.calculateCodeDiscount("CODE101"));
        assertEquals( -5, businessService.calculateCodeDiscount("CODE05"));
        assertEquals( -10, businessService.calculateCodeDiscount("CODE10"));
        assertEquals( -15, businessService.calculateCodeDiscount("CODE15"));
        assertEquals( -20, businessService.calculateCodeDiscount("CODE20"));
        assertEquals( -25, businessService.calculateCodeDiscount("CODE25"));
        assertEquals( -50, businessService.calculateCodeDiscount("CODE50"));
        assertEquals( 0, businessService.calculateCodeDiscount("cadcada"));
        assertEquals( 0, businessService.calculateCodeDiscount("OWQIWO1232EX<"));

    }

    @Test
    void calculateTotalPriceBooking() throws Exception {

        BookingApiDTO book = new BookingApiDTO();
        CalculatedBookDTO calculationsExpected = new CalculatedBookDTO();
        CalculatedBookDTO calculated = new CalculatedBookDTO();
        RoomTypesModel roomType = new RoomTypesModel();
        RoomsModel room = new RoomsModel();

        room.setId(10);
        room.setCode("code");
        room.setPricePerNight(160.0);
        room.setDescription("description here");
        room.setGuests(5);
        room.setImage("image here");
        roomType.setRoomsById(new ArrayList<>());
        roomType.setId(10);
        roomType.setName("room name");
        roomType.setDescription("description here");
        room.setRoomtypesByFkRoomtypeId(roomType);


        book.setRoomId(room.getId());
        book.setCheckIn("2020-12-20");
        book.setCheckOut("2021-01-09");
        book.setGuests(String.valueOf(room.getGuests()));
        book.setBreakfastService(true);
        book.setCarParkingService(true);
        book.setSpaService(true);
        book.setLaundryService(true);
        book.setShuttleService(true);
        book.setCodeDiscount("CODE50aa");

        calculationsExpected.setTotalNights(20);
        calculationsExpected.setRoomPricePerNight(160);
        calculationsExpected.setRoomTotalPrice(2650);
        calculationsExpected.setBreakFastPricePerNight(4);
        calculationsExpected.setBreakFastTotalPrice(80);
        calculationsExpected.setCarParkingPricePerNight(10);
        calculationsExpected.setCarParkingTotalPrice(200);
        calculationsExpected.setSpaPricePerNight(5);
        calculationsExpected.setSpaTotalPrice(100);
        calculationsExpected.setLaundryPricePerNight(2);
        calculationsExpected.setLaundryTotalPrice(40);
        calculationsExpected.setShuttlePricePerNight(20);
        calculationsExpected.setShuttleTotalPrice(20);
        calculationsExpected.setCodeDiscountPrice(0);
        calculationsExpected.setTotalBookingPrice(3000);

        when(roomsService.findById(room.getId())).thenReturn(room);

        calculated = businessService.calculateTotalPriceBooking(book);
        assertEquals(calculationsExpected.totalBookingPrice, calculated.totalBookingPrice);
    }

}