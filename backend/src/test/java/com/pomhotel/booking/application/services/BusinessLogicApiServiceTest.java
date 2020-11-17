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
import static org.mockito.ArgumentMatchers.doubleThat;
import static org.mockito.Mockito.when;


@SpringBootTest
class BusinessLogicApiServiceTest {
    public enum RoomTypes { SuiteRoom (1), IndividualRoom(2), FamilyRoom(3), LuxuryRoom(4), DoubleRoom(5);
        private final long roomType;
        RoomTypes(long i) {
            this.roomType = i;
        }

        public long getRoomType() {
            return roomType;
        }
    }

    @MockBean
    private RoomsService roomsService;

    @Autowired
    private BusinessLogicApiServiceImplementation businessService;

    @Test
    public void ShouldInjectedBeanNeverShouldBeNull() throws Exception
    {
        assertThat(businessService).isNotNull();
    }

    @Test
    void getDaysBetweenTwoDates() {
        assertEquals(2, businessService.getDaysBetweenTwoDates(Date.valueOf("2020-01-01"), Date.valueOf("2020-01-03")));
        assertEquals(20, businessService.getDaysBetweenTwoDates(Date.valueOf("2020-01-01"), Date.valueOf("2020-01-21")));
        assertEquals(4, businessService.getDaysBetweenTwoDates(Date.valueOf("2021-01-30"), Date.valueOf("2021-02-03")));
    }

    @Test
    void calculateBasePrice() {
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
    void calculateBreakFastService() {
        assertEquals(6, businessService.calculateBasePrice(2,3));
    }

    @Test
    void calculateCarParkingService() {
        assertEquals(100, businessService.calculateBasePrice(10,10));
    }

    @Test
    void calculateSpaServiceInFunctionOfRoomTypeSuiteAndLuxuryShouldBeZero() {

        RoomTypesModel roomType = new RoomTypesModel();
        double priceCalculated = 0;

        roomType.setId(RoomTypes.SuiteRoom.getRoomType());
        priceCalculated = businessService.calculateSpaService(10,10,roomType);
        assertEquals(0, priceCalculated);

        roomType.setId(RoomTypes.IndividualRoom.getRoomType());
        priceCalculated = businessService.calculateSpaService(10,10,roomType);
        assertEquals(100, priceCalculated);

        roomType.setId(RoomTypes.FamilyRoom.getRoomType());
        priceCalculated = businessService.calculateSpaService(10,10,roomType);
        assertEquals(100, priceCalculated);

        roomType.setId(RoomTypes.LuxuryRoom.getRoomType());
        priceCalculated = businessService.calculateSpaService(10,10,roomType);
        assertEquals(0, priceCalculated);

        roomType.setId(RoomTypes.DoubleRoom.getRoomType());
        priceCalculated = businessService.calculateSpaService(10,10,roomType);
        assertEquals(100, priceCalculated);

    }

    @Test
    void calculateLaundryService() {
        assertEquals(0, businessService.calculateLaundryService(0,10));
        assertEquals(100, businessService.calculateLaundryService(10,10));
    }

    @Test
    void calculateShuttleService() {
        assertEquals(10, businessService.calculateShuttleService(10));
    }

    @Test
    void calculateDiscountIfCodeDiscountIsValid() {

        final double zeroeuroNoValidCode = 0;
        final double minus5euro = -5;
        final double minus10euro = -10;
        final double minus15euro = -15;
        final double minus20euro = -20;
        final double minus25euro = -25;
        final double minus50euro = -50;

        double discountCalculated = 0;

        discountCalculated = businessService.calculateCodeDiscount("CODE05");
        assertEquals( minus5euro, discountCalculated);

        discountCalculated = businessService.calculateCodeDiscount("CODE10");
        assertEquals( minus10euro, discountCalculated);

        discountCalculated = businessService.calculateCodeDiscount("CODE15");
        assertEquals( minus15euro, discountCalculated);

        discountCalculated = businessService.calculateCodeDiscount("CODE20");
        assertEquals( minus20euro, discountCalculated);

        discountCalculated = businessService.calculateCodeDiscount("CODE25");
        assertEquals( minus25euro, discountCalculated);

        discountCalculated = businessService.calculateCodeDiscount("CODE50");
        assertEquals( minus50euro, discountCalculated);

        discountCalculated = businessService.calculateCodeDiscount("CODE101");
        assertEquals( zeroeuroNoValidCode, discountCalculated);

        discountCalculated = businessService.calculateCodeDiscount("cadcada");
        assertEquals( zeroeuroNoValidCode, discountCalculated);

        discountCalculated = businessService.calculateCodeDiscount("OWQIWO1232EX");
        assertEquals( zeroeuroNoValidCode, discountCalculated);

        discountCalculated = businessService.calculateCodeDiscount("CODENOVALID");
        assertEquals( zeroeuroNoValidCode, discountCalculated);


    }

    @Test //TODO better name
    void calculateTotalPriceBooking() {

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

    // todo crear mas casos de test desde el mas sencillo al mas complejo
    // todo nombres bien descriptivos

    @Test
    void GivenAnyRoomOneNightNoAdditionalServicesShouldBeReturnRoomPrice() {

    }

    @Test
    void GivenAnyRoomOneNightWithBreakfast_ShouldBeReturnSumOfRoomBreakfastPrice() {

    }

    @Test
    void GivenAnyRoomOneNightWithBreakfastCarParking_ShouldBeReturnSumOfRoomBreakfastCarParkingPrice() {

    }

    @Test
    void GivenAnyRoomOneNightWithBreakfastCarParkingLaundry_ShouldBeReturnSumOfRoomBreakfastCarParkingLaundryPrice() {

    }

    @Test
    void GivenAnyRoomOneNightWithBreakfastCarParkingLaundryShuttle_ShouldBeReturnSumOfRoomBreakfastCarParkingLaundryShuttlePrice() {

    }

    @Test
    void GivenARoomThatIsNotLuxuryOrSuiteWithBreakfastCarParkingLaundryShuttleSPA_ShouldBeReturnSumOfRoomBreakfastCarParkingLaundryShuttleSPAPrice() {

    }

    //SPA is a service included in Luxury Or Suite rooms
    @Test
    void GivenALuxuryOrSuiteWithBreakfastCarParkingLaundryShuttleSPA_ShouldBeReturnSumOfRoomBreakfastCarParkingLaundryShuttlePrice_SPAServiceIsIncludedWithTheseRooms() {

    }

    // Stay less than 20 nights normal price of room
    @Test
    void GivenAnyRoomWithAStayLessThan20NightsRoomPrice_ShouldBeCalcNormalPriceRoomPerNight(){

    }

    // Stay greater than or equal 20 nights 20% discount on room price
    @Test
    void GivenAnyRoomWithAStayGreaterThanOrEqualTo20NightsRoomPrice_ShouldBeApply20percentOfDiscountOnRoomPricePerNight(){

    }

}