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

    BookingApiDTO book = new BookingApiDTO();
    CalculatedBookDTO calculationsExpected = new CalculatedBookDTO();
    CalculatedBookDTO calculated = new CalculatedBookDTO();
    RoomTypesModel roomType = new RoomTypesModel();
    RoomsModel room = new RoomsModel();

    final double zeroeuroNoValidCode = 0;
    final double minus5euro = -5;
    final double minus10euro = -10;
    final double minus15euro = -15;
    final double minus20euro = -20;
    final double minus25euro = -25;
    final double minus50euro = -50;

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
    public void CheckIfServiceIsCorrectInjectedBean_NeverShouldBeNull() {
        assertThat(businessService).isNotNull();

    }

    @Test
    public void GivenANewInstanceOfBusinessLogicApiServiceImplementation_NeverShouldBeNull() {
        BusinessLogicApiService instanceOfBL = new BusinessLogicApiServiceImplementation();
        assertThat(instanceOfBL).isNotNull();

    }


    @Test
    void GivenAnBookingApiDTO_ShouldBeReturnACalculateBookDTO() {
        assertEquals(false,true);

    }

    @Test
    void GivenAnBookingsModel_ShouldBeReturnACalculateBookDTO() {
        assertEquals(false,true);

    }

    @Test
    void GivenTwoDates_ShouldBeReturnNumberOfDays() {
        assertEquals(2, businessService.getDaysBetweenTwoDates(Date.valueOf("2020-01-01"), Date.valueOf("2020-01-03")));
        assertEquals(20, businessService.getDaysBetweenTwoDates(Date.valueOf("2020-01-01"), Date.valueOf("2020-01-21")));
        assertEquals(4, businessService.getDaysBetweenTwoDates(Date.valueOf("2021-01-30"), Date.valueOf("2021-02-03")));

    }

    @Test
    void CalcOfBasePrice_GivenAnyNumberOfNightAndAnyPricePerNight_ShouldBeReturnMultiply() {
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
    void CalcOfBreakfast_GivenAnyNumberOfNightAndAnyPricePerNight_ShouldBeReturnMultiply() {
        assertEquals(6, businessService.calculateBasePrice(2,3));

    }

    @Test
    void CalcOfCarParking_GivenAnyNumberOfNightAndAnyPricePerNight_ShouldBeReturnMultiply() {
        assertEquals(100, businessService.calculateBasePrice(10,10));

    }


    @Test
    void CalcOfLaundry_GivenAnyNumberOfNightAndAnyPricePerNight_ShouldBeReturnMultiply() {
        assertEquals(0, businessService.calculateLaundryService(0,10));
        assertEquals(100, businessService.calculateLaundryService(10,10));

    }

    @Test
    void CalcOfShuttle_GivenAnyPrice_ShouldBeReturnThatPrice_ShuttleServiceOnlyChargedOnce() {
        assertEquals(10, businessService.calculateShuttleService(10));

    }

    @Test
    void CheckOfDiscountCode_CODE05_GivenStringWithValue_CODE05_ShouldBeReturnMinus5_WithAnyOtherStringValueShouldBeReturnZero() {
        double discountCalculated;

        discountCalculated = businessService.calculateCodeDiscount("CODE05");
        assertEquals( minus5euro, discountCalculated);

    }

    @Test
    void CheckOfDiscountCode_CODE10_GivenStringWithValue_CODE10_ShouldBeReturnMinus10_WithAnyOtherStringValueShouldBeReturnZero() {
        double discountCalculated;

        discountCalculated = businessService.calculateCodeDiscount("CODE10");
        assertEquals( minus10euro, discountCalculated);

    }

    @Test
    void CheckOfDiscountCode_CODE15_GivenStringWithValue_CODE15_ShouldBeReturnMinus15_WithAnyOtherStringValueShouldBeReturnZero() {
        double discountCalculated;

        discountCalculated = businessService.calculateCodeDiscount("CODE15");
        assertEquals( minus15euro, discountCalculated);

    }

    @Test
    void CheckOfDiscountCode_CODE20_GivenStringWithValue_CODE20_ShouldBeReturnMinus20_WithAnyOtherStringValueShouldBeReturnZero() {
        double discountCalculated;

        discountCalculated = businessService.calculateCodeDiscount("CODE20");
        assertEquals( minus20euro, discountCalculated);

    }

    @Test
    void CheckOfDiscountCode_CODE25_GivenStringWithValue_CODE25_ShouldBeReturnMinus25_WithAnyOtherStringValueShouldBeReturnZero() {
        double discountCalculated;

        discountCalculated = businessService.calculateCodeDiscount("CODE25");
        assertEquals( minus25euro, discountCalculated);

    }

    @Test
    void CheckOfDiscountCode_CODE50_GivenStringWithValue_CODE50_ShouldBeReturnMinus50_WithAnyOtherStringValueShouldBeReturnZero() {
        double discountCalculated;

        discountCalculated = businessService.calculateCodeDiscount("CODE50");
        assertEquals( minus50euro, discountCalculated);

    }

    @Test
    void CheckOfDiscountCode_GivenAnyEmptyStringValue_ShouldBeReturnZero() {
        double discountCalculated;

        discountCalculated = businessService.calculateCodeDiscount("");
        assertEquals( zeroeuroNoValidCode, discountCalculated);

    }


    @Test
    void CheckOfDiscountCode_GivenAnyStringValue_ShouldBeReturnZero() {
        double discountCalculated;

        discountCalculated = businessService.calculateCodeDiscount("cadcda31|2@");
        assertEquals( zeroeuroNoValidCode, discountCalculated);

    }

    // todo crear mas casos de test desde el mas sencillo al mas complejo
    // todo nombres bien descriptivos

    @Test
    void GivenAnyRoomOneNightNoAdditionalServicesShouldBeReturnRoomPrice() {
        // Arrange

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

        // Act
        calculated = businessService.calculateTotalPriceBooking(book);

        // Asserts
        assertEquals(calculationsExpected.totalBookingPrice, calculated.totalBookingPrice);
        assertEquals(false,true);

    }

    @Test
    void GivenAnyRoomOneNightWithBreakfast_ShouldBeReturnSumOfRoomBreakfastPrice() {
        assertEquals(false,true);

    }

    @Test
    void GivenAnyRoomOneNightWithBreakfastCarParking_ShouldBeReturnSumOfRoomBreakfastCarParkingPrice() {
        assertEquals(false,true);

    }

    @Test
    void GivenAnyRoomOneNightWithBreakfastCarParkingLaundry_ShouldBeReturnSumOfRoomBreakfastCarParkingLaundryPrice() {
        assertEquals(false,true);

    }

    @Test
    void GivenAnyRoomOneNightWithBreakfastCarParkingLaundryShuttle_ShouldBeReturnSumOfRoomBreakfastCarParkingLaundryShuttlePrice() {
        assertEquals(false,true);

    }

    //SPA is a service included in Suite
    @Test
    void GivenASuiteWithBreakfastCarParkingLaundryShuttleSPA_ShouldBeReturnSumOfRoomBreakfastCarParkingLaundryShuttlePrice_SPAServiceIsIncludedWithTheseRooms() {
        RoomTypesModel roomType = new RoomTypesModel();
        double priceCalculated;

        roomType.setId(RoomTypes.SuiteRoom.getRoomType());
        priceCalculated = businessService.calculateSpaService(10,10,roomType);
        assertEquals(111, priceCalculated);

    }


    //SPA service with Individual Room
    @Test
    void GivenAnIndividualRoomWithBreakfastCarParkingLaundryShuttleSPA_ShouldBeReturnSumOfRoomBreakfastCarParkingLaundryShuttleSPAPrice() {
        RoomTypesModel roomType = new RoomTypesModel();
        double priceCalculated;

        roomType.setId(RoomTypes.IndividualRoom.getRoomType());
        priceCalculated = businessService.calculateSpaService(10,10,roomType);
        assertEquals(111, priceCalculated);

    }

    //SPA service with Familiy Room
    @Test
    void GivenAnFamilyRoomWithBreakfastCarParkingLaundryShuttleSPA_ShouldBeReturnSumOfRoomBreakfastCarParkingLaundryShuttleSPAPrice() {
        RoomTypesModel roomType = new RoomTypesModel();
        double priceCalculated;

        roomType.setId(RoomTypes.FamilyRoom.getRoomType());
        priceCalculated = businessService.calculateSpaService(10,10,roomType);
        assertEquals(111, priceCalculated);

    }

    //SPA is a service included in Luxury
    @Test
    void GivenALuxuryWithBreakfastCarParkingLaundryShuttleSPA_ShouldBeReturnSumOfRoomBreakfastCarParkingLaundryShuttlePrice_SPAServiceIsIncludedWithTheseRooms() {
        RoomTypesModel roomType = new RoomTypesModel();
        double priceCalculated;

        roomType.setId(RoomTypes.LuxuryRoom.getRoomType());
        priceCalculated = businessService.calculateSpaService(10,10,roomType);
        assertEquals(111, priceCalculated);

    }

    //SPA service with Double Room
    @Test
    void GivenAnDoubleRoomWithBreakfastCarParkingLaundryShuttleSPA_ShouldBeReturnSumOfRoomBreakfastCarParkingLaundryShuttleSPAPrice() {
        RoomTypesModel roomType = new RoomTypesModel();
        double priceCalculated;

        roomType.setId(RoomTypes.DoubleRoom.getRoomType());
        priceCalculated = businessService.calculateSpaService(10,10,roomType);
        assertEquals(111, priceCalculated);

    }


    // Stay less than 20 nights normal price of room
    @Test
    void GivenAnyRoomWithAStayLessThan20NightsRoomPrice_ShouldBeCalcNormalPriceRoomPerNight(){
        assertEquals(false,true);

    }

    // Stay greater than or equal 20 nights 20% discount on room price
    @Test
    void GivenAnyRoomWithAStayGreaterThanOrEqualTo20NightsRoomPrice_ShouldBeApply20percentOfDiscountOnRoomPricePerNight(){
        assertEquals(false,true);

    }

}