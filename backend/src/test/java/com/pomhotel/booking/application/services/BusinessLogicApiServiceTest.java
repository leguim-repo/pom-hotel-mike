package com.pomhotel.booking.application.services;

import com.pomhotel.booking.application.models.BookingsModel;
import com.pomhotel.booking.application.models.RoomTypesModel;
import com.pomhotel.booking.application.models.RoomsModel;
import com.pomhotel.booking.ui.api.v1.dto.BookingApiDTO;
import com.pomhotel.booking.ui.api.v1.dto.CalculatedBookDTO;
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

    public enum RoomTypes { SuiteRoom (1), IndividualRoom(2), FamilyRoom(3), LuxuryRoom(4), DoubleRoom(5), UnkwnowRoom(7);
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
    void GivenAnBookingApiDTO_ShouldBeReturnACalculateBookDTO_SimpleTestFoMethodcallToCalculateBooking() {
        // Arrange
        room.setId(5);
        room.setCode("");
        room.setPricePerNight(50.0);
        room.setDescription("description here");
        room.setGuests(1);
        room.setImage("image here");
        roomType.setRoomsById(new ArrayList<>());
        roomType.setId(RoomTypes.IndividualRoom.getRoomType());
        roomType.setName("room name");
        roomType.setDescription("description here");
        room.setRoomtypesByFkRoomtypeId(roomType);

        book.setRoomId(room.getId());
        book.setCheckIn("2021-01-01");
        book.setCheckOut("2021-01-02");
        book.setGuests(String.valueOf(room.getGuests()));
        book.setBreakfastService(false);
        book.setCarParkingService(false);
        book.setSpaService(false);
        book.setLaundryService(false);
        book.setShuttleService(false);
        book.setCodeDiscount("");

        calculationsExpected.setTotalNights(1);
        calculationsExpected.setLongStay(false);
        calculationsExpected.setRoomPricePerNight(room.getPricePerNight());
        calculationsExpected.setRoomSpecialPricePerNight(0);
        calculationsExpected.setRoomSpecialTotalPrice(0);
        calculationsExpected.setRoomTotalPrice(room.getPricePerNight() * calculationsExpected.getTotalNights());
        calculationsExpected.setBreakFastPricePerNight(0);
        calculationsExpected.setBreakFastTotalPrice(0);
        calculationsExpected.setCarParkingPricePerNight(0);
        calculationsExpected.setCarParkingTotalPrice(0);
        calculationsExpected.setSpaPricePerNight(0);
        calculationsExpected.setSpaTotalPrice(0);
        calculationsExpected.setLaundryPricePerNight(0);
        calculationsExpected.setLaundryTotalPrice(0);
        calculationsExpected.setShuttlePricePerNight(0);
        calculationsExpected.setShuttleTotalPrice(0);
        calculationsExpected.setCodeDiscountPrice(0);
        calculationsExpected.setTotalBookingPrice(50.0);

        when(roomsService.findById(room.getId())).thenReturn(room);

        // Act
        calculated = businessService.callToCalculateBooking(book);

        // Asserts
        assertEquals(calculationsExpected.isLongStay(), calculated.isLongStay());
        assertEquals(calculationsExpected.totalNights, calculated.totalNights);
        assertEquals(calculationsExpected.roomPricePerNight, calculated.roomPricePerNight);
        assertEquals(calculationsExpected.roomTotalPrice, calculated.roomTotalPrice);
        assertEquals(calculationsExpected.roomSpecialPricePerNight, calculated.roomSpecialPricePerNight);
        assertEquals(calculationsExpected.roomSpecialTotalPrice, calculated.roomSpecialTotalPrice);
        assertEquals(calculationsExpected.breakFastPricePerNight, calculated.breakFastPricePerNight);
        assertEquals(calculationsExpected.breakFastTotalPrice, calculated.breakFastTotalPrice);
        assertEquals(calculationsExpected.carParkingPricePerNight, calculated.carParkingPricePerNight);
        assertEquals(calculationsExpected.carParkingTotalPrice, calculated.carParkingTotalPrice);
        assertEquals(calculationsExpected.spaPricePerNight, calculated.spaPricePerNight);
        assertEquals(calculationsExpected.spaTotalPrice, calculated.spaTotalPrice);
        assertEquals(calculationsExpected.laundryPricePerNight, calculated.laundryPricePerNight);
        assertEquals(calculationsExpected.laundryTotalPrice, calculated.laundryTotalPrice);
        assertEquals(calculationsExpected.shuttlePricePerNight, calculated.shuttlePricePerNight);
        assertEquals(calculationsExpected.shuttleTotalPrice, calculated.shuttleTotalPrice);
        assertEquals(calculationsExpected.codeDiscountPrice, calculated.codeDiscountPrice);
        assertEquals(calculationsExpected.totalBookingPrice, calculated.totalBookingPrice);

    }

    @Test
    void GivenAnBookingModel_ShouldBeReturnACalculateBookDTO_SimpleTestFoMethodcallToCalculateBooking() {
        BookingsModel bookModel = new BookingsModel();
        // Arrange
        room.setId(5);
        room.setCode("");
        room.setPricePerNight(50.0);
        room.setDescription("description here");
        room.setGuests(1);
        room.setImage("image here");
        roomType.setRoomsById(new ArrayList<>());
        roomType.setId(RoomTypes.IndividualRoom.getRoomType());
        roomType.setName("room name");
        roomType.setDescription("description here");
        room.setRoomtypesByFkRoomtypeId(roomType);

        bookModel.setRoomsByFKRoomId(room);
        bookModel.setCheckIn(Date.valueOf("2021-01-01"));
        bookModel.setCheckOut(Date.valueOf("2021-01-02"));
        bookModel.setGuests(room.getGuests());
        bookModel.setBreakfast(false);
        bookModel.setCarparking(false);
        bookModel.setSpa(false);
        bookModel.setLaundry(false);
        bookModel.setShuttle(false);
        bookModel.setCodediscount("");

        calculationsExpected.setTotalNights(1);
        calculationsExpected.setLongStay(false);
        calculationsExpected.setRoomPricePerNight(room.getPricePerNight());
        calculationsExpected.setRoomSpecialPricePerNight(0);
        calculationsExpected.setRoomSpecialTotalPrice(0);
        calculationsExpected.setRoomTotalPrice(room.getPricePerNight() * calculationsExpected.getTotalNights());
        calculationsExpected.setBreakFastPricePerNight(0);
        calculationsExpected.setBreakFastTotalPrice(0);
        calculationsExpected.setCarParkingPricePerNight(0);
        calculationsExpected.setCarParkingTotalPrice(0);
        calculationsExpected.setSpaPricePerNight(0);
        calculationsExpected.setSpaTotalPrice(0);
        calculationsExpected.setLaundryPricePerNight(0);
        calculationsExpected.setLaundryTotalPrice(0);
        calculationsExpected.setShuttlePricePerNight(0);
        calculationsExpected.setShuttleTotalPrice(0);
        calculationsExpected.setCodeDiscountPrice(0);
        calculationsExpected.setTotalBookingPrice(50.0);

        when(roomsService.findById(room.getId())).thenReturn(room);

        // Act
        calculated = businessService.callToCalculateBooking(bookModel);

        // Asserts
        assertEquals(calculationsExpected.isLongStay(), calculated.isLongStay());
        assertEquals(calculationsExpected.totalNights, calculated.totalNights);
        assertEquals(calculationsExpected.roomPricePerNight, calculated.roomPricePerNight);
        assertEquals(calculationsExpected.roomTotalPrice, calculated.roomTotalPrice);
        assertEquals(calculationsExpected.roomSpecialPricePerNight, calculated.roomSpecialPricePerNight);
        assertEquals(calculationsExpected.roomSpecialTotalPrice, calculated.roomSpecialTotalPrice);
        assertEquals(calculationsExpected.breakFastPricePerNight, calculated.breakFastPricePerNight);
        assertEquals(calculationsExpected.breakFastTotalPrice, calculated.breakFastTotalPrice);
        assertEquals(calculationsExpected.carParkingPricePerNight, calculated.carParkingPricePerNight);
        assertEquals(calculationsExpected.carParkingTotalPrice, calculated.carParkingTotalPrice);
        assertEquals(calculationsExpected.spaPricePerNight, calculated.spaPricePerNight);
        assertEquals(calculationsExpected.spaTotalPrice, calculated.spaTotalPrice);
        assertEquals(calculationsExpected.laundryPricePerNight, calculated.laundryPricePerNight);
        assertEquals(calculationsExpected.laundryTotalPrice, calculated.laundryTotalPrice);
        assertEquals(calculationsExpected.shuttlePricePerNight, calculated.shuttlePricePerNight);
        assertEquals(calculationsExpected.shuttleTotalPrice, calculated.shuttleTotalPrice);
        assertEquals(calculationsExpected.codeDiscountPrice, calculated.codeDiscountPrice);
        assertEquals(calculationsExpected.totalBookingPrice, calculated.totalBookingPrice);

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
    void CheckOfDiscountCode_GivenAnyStringThatNotIsAValidCode_ShouldBeReturnZero() {
        double discountCalculated;

        discountCalculated = businessService.calculateCodeDiscount("cadcda31|2@");
        assertEquals( zeroeuroNoValidCode, discountCalculated);

    }

    // Simple case
    @Test
    void GivenAnyRoomOneNightNoAdditionalServices_ShouldBeReturnRoomPrice() {
        // Arrange
        room.setId(5);
        room.setCode("");
        room.setPricePerNight(50.0);
        room.setDescription("description here");
        room.setGuests(1);
        room.setImage("image here");
        roomType.setRoomsById(new ArrayList<>());
        roomType.setId(RoomTypes.IndividualRoom.getRoomType());
        roomType.setName("room name");
        roomType.setDescription("description here");
        room.setRoomtypesByFkRoomtypeId(roomType);

        book.setRoomId(room.getId());
        book.setCheckIn("2021-01-01");
        book.setCheckOut("2021-01-02");
        book.setGuests(String.valueOf(room.getGuests()));
        book.setBreakfastService(false);
        book.setCarParkingService(false);
        book.setSpaService(false);
        book.setLaundryService(false);
        book.setShuttleService(false);
        book.setCodeDiscount("");

        calculationsExpected.setTotalNights(1);
        calculationsExpected.setLongStay(false);
        calculationsExpected.setRoomPricePerNight(room.getPricePerNight());
        calculationsExpected.setRoomSpecialPricePerNight(0);
        calculationsExpected.setRoomSpecialTotalPrice(0);
        calculationsExpected.setRoomTotalPrice(room.getPricePerNight() * calculationsExpected.getTotalNights());
        calculationsExpected.setBreakFastPricePerNight(0);
        calculationsExpected.setBreakFastTotalPrice(0);
        calculationsExpected.setCarParkingPricePerNight(0);
        calculationsExpected.setCarParkingTotalPrice(0);
        calculationsExpected.setSpaPricePerNight(0);
        calculationsExpected.setSpaTotalPrice(0);
        calculationsExpected.setLaundryPricePerNight(0);
        calculationsExpected.setLaundryTotalPrice(0);
        calculationsExpected.setShuttlePricePerNight(0);
        calculationsExpected.setShuttleTotalPrice(0);
        calculationsExpected.setCodeDiscountPrice(0);
        calculationsExpected.setTotalBookingPrice(50.0);

        when(roomsService.findById(room.getId())).thenReturn(room);

        // Act
        calculated = businessService.calculateTotalPriceBooking(book);

        // Asserts
        assertEquals(calculationsExpected.isLongStay(), calculated.isLongStay());
        assertEquals(calculationsExpected.totalNights, calculated.totalNights);
        assertEquals(calculationsExpected.roomPricePerNight, calculated.roomPricePerNight);
        assertEquals(calculationsExpected.roomTotalPrice, calculated.roomTotalPrice);
        assertEquals(calculationsExpected.roomSpecialPricePerNight, calculated.roomSpecialPricePerNight);
        assertEquals(calculationsExpected.roomSpecialTotalPrice, calculated.roomSpecialTotalPrice);
        assertEquals(calculationsExpected.breakFastPricePerNight, calculated.breakFastPricePerNight);
        assertEquals(calculationsExpected.breakFastTotalPrice, calculated.breakFastTotalPrice);
        assertEquals(calculationsExpected.carParkingPricePerNight, calculated.carParkingPricePerNight);
        assertEquals(calculationsExpected.carParkingTotalPrice, calculated.carParkingTotalPrice);
        assertEquals(calculationsExpected.spaPricePerNight, calculated.spaPricePerNight);
        assertEquals(calculationsExpected.spaTotalPrice, calculated.spaTotalPrice);
        assertEquals(calculationsExpected.laundryPricePerNight, calculated.laundryPricePerNight);
        assertEquals(calculationsExpected.laundryTotalPrice, calculated.laundryTotalPrice);
        assertEquals(calculationsExpected.shuttlePricePerNight, calculated.shuttlePricePerNight);
        assertEquals(calculationsExpected.shuttleTotalPrice, calculated.shuttleTotalPrice);
        assertEquals(calculationsExpected.codeDiscountPrice, calculated.codeDiscountPrice);
        assertEquals(calculationsExpected.totalBookingPrice, calculated.totalBookingPrice);

    }

    @Test
    void GivenAnyRoomOneNightWithBreakfast_ShouldBeReturnSumOfRoomBreakfastPrice() {
        // Arrange
        room.setId(5);
        room.setCode("");
        room.setPricePerNight(50.0);
        room.setDescription("description here");
        room.setGuests(1);
        room.setImage("image here");
        roomType.setRoomsById(new ArrayList<>());
        roomType.setId(RoomTypes.IndividualRoom.getRoomType());
        roomType.setName("room name");
        roomType.setDescription("description here");
        room.setRoomtypesByFkRoomtypeId(roomType);

        book.setRoomId(room.getId());
        book.setCheckIn("2021-01-01");
        book.setCheckOut("2021-01-02");
        book.setGuests(String.valueOf(room.getGuests()));
        book.setBreakfastService(true);
        book.setCarParkingService(false);
        book.setSpaService(false);
        book.setLaundryService(false);
        book.setShuttleService(false);
        book.setCodeDiscount("");

        calculationsExpected.setTotalNights(1);
        calculationsExpected.setLongStay(false);
        calculationsExpected.setRoomPricePerNight(room.getPricePerNight());
        calculationsExpected.setRoomSpecialPricePerNight(0);
        calculationsExpected.setRoomSpecialTotalPrice(0);
        calculationsExpected.setRoomTotalPrice(room.getPricePerNight() * calculationsExpected.getTotalNights());
        calculationsExpected.setBreakFastPricePerNight(4);
        calculationsExpected.setBreakFastTotalPrice(4);
        calculationsExpected.setCarParkingPricePerNight(0);
        calculationsExpected.setCarParkingTotalPrice(0);
        calculationsExpected.setSpaPricePerNight(0);
        calculationsExpected.setSpaTotalPrice(0);
        calculationsExpected.setLaundryPricePerNight(0);
        calculationsExpected.setLaundryTotalPrice(0);
        calculationsExpected.setShuttlePricePerNight(0);
        calculationsExpected.setShuttleTotalPrice(0);
        calculationsExpected.setCodeDiscountPrice(0);
        calculationsExpected.setTotalBookingPrice(54.0);

        when(roomsService.findById(room.getId())).thenReturn(room);

        // Act
        calculated = businessService.calculateTotalPriceBooking(book);

        // Asserts
        assertEquals(calculationsExpected.isLongStay(), calculated.isLongStay());
        assertEquals(calculationsExpected.totalNights, calculated.totalNights);
        assertEquals(calculationsExpected.roomPricePerNight, calculated.roomPricePerNight);
        assertEquals(calculationsExpected.roomTotalPrice, calculated.roomTotalPrice);
        assertEquals(calculationsExpected.roomSpecialPricePerNight, calculated.roomSpecialPricePerNight);
        assertEquals(calculationsExpected.roomSpecialTotalPrice, calculated.roomSpecialTotalPrice);
        assertEquals(calculationsExpected.breakFastPricePerNight, calculated.breakFastPricePerNight);
        assertEquals(calculationsExpected.breakFastTotalPrice, calculated.breakFastTotalPrice);
        assertEquals(calculationsExpected.carParkingPricePerNight, calculated.carParkingPricePerNight);
        assertEquals(calculationsExpected.carParkingTotalPrice, calculated.carParkingTotalPrice);
        assertEquals(calculationsExpected.spaPricePerNight, calculated.spaPricePerNight);
        assertEquals(calculationsExpected.spaTotalPrice, calculated.spaTotalPrice);
        assertEquals(calculationsExpected.laundryPricePerNight, calculated.laundryPricePerNight);
        assertEquals(calculationsExpected.laundryTotalPrice, calculated.laundryTotalPrice);
        assertEquals(calculationsExpected.shuttlePricePerNight, calculated.shuttlePricePerNight);
        assertEquals(calculationsExpected.shuttleTotalPrice, calculated.shuttleTotalPrice);
        assertEquals(calculationsExpected.codeDiscountPrice, calculated.codeDiscountPrice);
        assertEquals(calculationsExpected.totalBookingPrice, calculated.totalBookingPrice);

    }

    @Test
    void GivenAnyRoomOneNightWithBreakfastCarParking_ShouldBeReturnSumOfRoomBreakfastCarParkingPrice() {
        // Arrange
        room.setId(5);
        room.setCode("");
        room.setPricePerNight(50.0);
        room.setDescription("description here");
        room.setGuests(1);
        room.setImage("image here");
        roomType.setRoomsById(new ArrayList<>());
        roomType.setId(RoomTypes.IndividualRoom.getRoomType());
        roomType.setName("room name");
        roomType.setDescription("description here");
        room.setRoomtypesByFkRoomtypeId(roomType);

        book.setRoomId(room.getId());
        book.setCheckIn("2021-01-01");
        book.setCheckOut("2021-01-02");
        book.setGuests(String.valueOf(room.getGuests()));
        book.setBreakfastService(true);
        book.setCarParkingService(true);
        book.setSpaService(false);
        book.setLaundryService(false);
        book.setShuttleService(false);
        book.setCodeDiscount("");

        calculationsExpected.setTotalNights(1);
        calculationsExpected.setLongStay(false);
        calculationsExpected.setRoomPricePerNight(room.getPricePerNight());
        calculationsExpected.setRoomSpecialPricePerNight(0);
        calculationsExpected.setRoomSpecialTotalPrice(0);
        calculationsExpected.setRoomTotalPrice(room.getPricePerNight() * calculationsExpected.getTotalNights());
        calculationsExpected.setBreakFastPricePerNight(4);
        calculationsExpected.setBreakFastTotalPrice(4);
        calculationsExpected.setCarParkingPricePerNight(10);
        calculationsExpected.setCarParkingTotalPrice(10);
        calculationsExpected.setSpaPricePerNight(0);
        calculationsExpected.setSpaTotalPrice(0);
        calculationsExpected.setLaundryPricePerNight(0);
        calculationsExpected.setLaundryTotalPrice(0);
        calculationsExpected.setShuttlePricePerNight(0);
        calculationsExpected.setShuttleTotalPrice(0);
        calculationsExpected.setCodeDiscountPrice(0);
        calculationsExpected.setTotalBookingPrice(64.0);

        when(roomsService.findById(room.getId())).thenReturn(room);

        // Act
        calculated = businessService.calculateTotalPriceBooking(book);

        // Asserts
        assertEquals(calculationsExpected.isLongStay(), calculated.isLongStay());
        assertEquals(calculationsExpected.totalNights, calculated.totalNights);
        assertEquals(calculationsExpected.roomPricePerNight, calculated.roomPricePerNight);
        assertEquals(calculationsExpected.roomTotalPrice, calculated.roomTotalPrice);
        assertEquals(calculationsExpected.roomSpecialPricePerNight, calculated.roomSpecialPricePerNight);
        assertEquals(calculationsExpected.roomSpecialTotalPrice, calculated.roomSpecialTotalPrice);
        assertEquals(calculationsExpected.breakFastPricePerNight, calculated.breakFastPricePerNight);
        assertEquals(calculationsExpected.breakFastTotalPrice, calculated.breakFastTotalPrice);
        assertEquals(calculationsExpected.carParkingPricePerNight, calculated.carParkingPricePerNight);
        assertEquals(calculationsExpected.carParkingTotalPrice, calculated.carParkingTotalPrice);
        assertEquals(calculationsExpected.spaPricePerNight, calculated.spaPricePerNight);
        assertEquals(calculationsExpected.spaTotalPrice, calculated.spaTotalPrice);
        assertEquals(calculationsExpected.laundryPricePerNight, calculated.laundryPricePerNight);
        assertEquals(calculationsExpected.laundryTotalPrice, calculated.laundryTotalPrice);
        assertEquals(calculationsExpected.shuttlePricePerNight, calculated.shuttlePricePerNight);
        assertEquals(calculationsExpected.shuttleTotalPrice, calculated.shuttleTotalPrice);
        assertEquals(calculationsExpected.codeDiscountPrice, calculated.codeDiscountPrice);
        assertEquals(calculationsExpected.totalBookingPrice, calculated.totalBookingPrice);

    }

    @Test
    void GivenAnyRoomOneNightWithBreakfastCarParkingLaundry_ShouldBeReturnSumOfRoomBreakfastCarParkingLaundryPrice() {
        // Arrange
        room.setId(5);
        room.setCode("");
        room.setPricePerNight(50.0);
        room.setDescription("description here");
        room.setGuests(1);
        room.setImage("image here");
        roomType.setRoomsById(new ArrayList<>());
        roomType.setId(RoomTypes.IndividualRoom.getRoomType());
        roomType.setName("room name");
        roomType.setDescription("description here");
        room.setRoomtypesByFkRoomtypeId(roomType);

        book.setRoomId(room.getId());
        book.setCheckIn("2021-01-01");
        book.setCheckOut("2021-01-02");
        book.setGuests(String.valueOf(room.getGuests()));
        book.setBreakfastService(true);
        book.setCarParkingService(true);
        book.setSpaService(false);
        book.setLaundryService(true);
        book.setShuttleService(false);
        book.setCodeDiscount("");

        calculationsExpected.setTotalNights(1);
        calculationsExpected.setLongStay(false);
        calculationsExpected.setRoomPricePerNight(room.getPricePerNight());
        calculationsExpected.setRoomSpecialPricePerNight(0);
        calculationsExpected.setRoomSpecialTotalPrice(0);
        calculationsExpected.setRoomTotalPrice(room.getPricePerNight() * calculationsExpected.getTotalNights());
        calculationsExpected.setBreakFastPricePerNight(4);
        calculationsExpected.setBreakFastTotalPrice(4);
        calculationsExpected.setCarParkingPricePerNight(10);
        calculationsExpected.setCarParkingTotalPrice(10);
        calculationsExpected.setSpaPricePerNight(0);
        calculationsExpected.setSpaTotalPrice(0);
        calculationsExpected.setLaundryPricePerNight(2);
        calculationsExpected.setLaundryTotalPrice(2);
        calculationsExpected.setShuttlePricePerNight(0);
        calculationsExpected.setShuttleTotalPrice(0);
        calculationsExpected.setCodeDiscountPrice(0);
        calculationsExpected.setTotalBookingPrice(66.0);

        when(roomsService.findById(room.getId())).thenReturn(room);

        // Act
        calculated = businessService.calculateTotalPriceBooking(book);

        // Asserts
        assertEquals(calculationsExpected.isLongStay(), calculated.isLongStay());
        assertEquals(calculationsExpected.totalNights, calculated.totalNights);
        assertEquals(calculationsExpected.roomPricePerNight, calculated.roomPricePerNight);
        assertEquals(calculationsExpected.roomTotalPrice, calculated.roomTotalPrice);
        assertEquals(calculationsExpected.roomSpecialPricePerNight, calculated.roomSpecialPricePerNight);
        assertEquals(calculationsExpected.roomSpecialTotalPrice, calculated.roomSpecialTotalPrice);
        assertEquals(calculationsExpected.breakFastPricePerNight, calculated.breakFastPricePerNight);
        assertEquals(calculationsExpected.breakFastTotalPrice, calculated.breakFastTotalPrice);
        assertEquals(calculationsExpected.carParkingPricePerNight, calculated.carParkingPricePerNight);
        assertEquals(calculationsExpected.carParkingTotalPrice, calculated.carParkingTotalPrice);
        assertEquals(calculationsExpected.spaPricePerNight, calculated.spaPricePerNight);
        assertEquals(calculationsExpected.spaTotalPrice, calculated.spaTotalPrice);
        assertEquals(calculationsExpected.laundryPricePerNight, calculated.laundryPricePerNight);
        assertEquals(calculationsExpected.laundryTotalPrice, calculated.laundryTotalPrice);
        assertEquals(calculationsExpected.shuttlePricePerNight, calculated.shuttlePricePerNight);
        assertEquals(calculationsExpected.shuttleTotalPrice, calculated.shuttleTotalPrice);
        assertEquals(calculationsExpected.codeDiscountPrice, calculated.codeDiscountPrice);
        assertEquals(calculationsExpected.totalBookingPrice, calculated.totalBookingPrice);

    }

    @Test
    void GivenAnyRoomOneNightWithBreakfastCarParkingLaundryShuttle_ShouldBeReturnSumOfRoomBreakfastCarParkingLaundryShuttlePrice() {
        // Arrange
        room.setId(5);
        room.setCode("");
        room.setPricePerNight(50.0);
        room.setDescription("description here");
        room.setGuests(1);
        room.setImage("image here");
        roomType.setRoomsById(new ArrayList<>());
        roomType.setId(RoomTypes.IndividualRoom.getRoomType());
        roomType.setName("room name");
        roomType.setDescription("description here");
        room.setRoomtypesByFkRoomtypeId(roomType);

        book.setRoomId(room.getId());
        book.setCheckIn("2021-01-01");
        book.setCheckOut("2021-01-02");
        book.setGuests(String.valueOf(room.getGuests()));
        book.setBreakfastService(true);
        book.setCarParkingService(true);
        book.setSpaService(false);
        book.setLaundryService(true);
        book.setShuttleService(true);
        book.setCodeDiscount("");

        calculationsExpected.setTotalNights(1);
        calculationsExpected.setLongStay(false);
        calculationsExpected.setRoomPricePerNight(room.getPricePerNight());
        calculationsExpected.setRoomSpecialPricePerNight(0);
        calculationsExpected.setRoomSpecialTotalPrice(0);
        calculationsExpected.setRoomTotalPrice(room.getPricePerNight() * calculationsExpected.getTotalNights());
        calculationsExpected.setBreakFastPricePerNight(4);
        calculationsExpected.setBreakFastTotalPrice(4);
        calculationsExpected.setCarParkingPricePerNight(10);
        calculationsExpected.setCarParkingTotalPrice(10);
        calculationsExpected.setSpaPricePerNight(0);
        calculationsExpected.setSpaTotalPrice(0);
        calculationsExpected.setLaundryPricePerNight(2);
        calculationsExpected.setLaundryTotalPrice(2);
        calculationsExpected.setShuttlePricePerNight(20.0);
        calculationsExpected.setShuttleTotalPrice(20.0);
        calculationsExpected.setCodeDiscountPrice(0);
        calculationsExpected.setTotalBookingPrice(86.0);

        when(roomsService.findById(room.getId())).thenReturn(room);

        // Act
        calculated = businessService.calculateTotalPriceBooking(book);

        // Asserts
        assertEquals(calculationsExpected.isLongStay(), calculated.isLongStay());
        assertEquals(calculationsExpected.totalNights, calculated.totalNights);
        assertEquals(calculationsExpected.roomPricePerNight, calculated.roomPricePerNight);
        assertEquals(calculationsExpected.roomTotalPrice, calculated.roomTotalPrice);
        assertEquals(calculationsExpected.roomSpecialPricePerNight, calculated.roomSpecialPricePerNight);
        assertEquals(calculationsExpected.roomSpecialTotalPrice, calculated.roomSpecialTotalPrice);
        assertEquals(calculationsExpected.breakFastPricePerNight, calculated.breakFastPricePerNight);
        assertEquals(calculationsExpected.breakFastTotalPrice, calculated.breakFastTotalPrice);
        assertEquals(calculationsExpected.carParkingPricePerNight, calculated.carParkingPricePerNight);
        assertEquals(calculationsExpected.carParkingTotalPrice, calculated.carParkingTotalPrice);
        assertEquals(calculationsExpected.spaPricePerNight, calculated.spaPricePerNight);
        assertEquals(calculationsExpected.spaTotalPrice, calculated.spaTotalPrice);
        assertEquals(calculationsExpected.laundryPricePerNight, calculated.laundryPricePerNight);
        assertEquals(calculationsExpected.laundryTotalPrice, calculated.laundryTotalPrice);
        assertEquals(calculationsExpected.shuttlePricePerNight, calculated.shuttlePricePerNight);
        assertEquals(calculationsExpected.shuttleTotalPrice, calculated.shuttleTotalPrice);
        assertEquals(calculationsExpected.codeDiscountPrice, calculated.codeDiscountPrice);
        assertEquals(calculationsExpected.totalBookingPrice, calculated.totalBookingPrice);

    }

    //SPA is a service included in Suite
    @Test
    void GivenASuiteWithBreakfastCarParkingLaundryShuttleSPA_ShouldBeReturnSumOfRoomBreakfastCarParkingLaundryShuttlePrice_SPAServiceIsIncludedWithTheseRooms() {
        // Arrange
        room.setId(1);
        room.setCode("");
        room.setPricePerNight(300.0);
        room.setDescription("description here");
        room.setGuests(2);
        room.setImage("image here");
        roomType.setRoomsById(new ArrayList<>());
        roomType.setId(RoomTypes.SuiteRoom.getRoomType());
        roomType.setName("room name");
        roomType.setDescription("description here");
        room.setRoomtypesByFkRoomtypeId(roomType);

        book.setRoomId(room.getId());
        book.setCheckIn("2021-01-01");
        book.setCheckOut("2021-01-02");
        book.setGuests(String.valueOf(room.getGuests()));
        book.setBreakfastService(true);
        book.setCarParkingService(true);
        book.setSpaService(true);
        book.setLaundryService(true);
        book.setShuttleService(true);
        book.setCodeDiscount("");

        calculationsExpected.setTotalNights(1);
        calculationsExpected.setLongStay(false);
        calculationsExpected.setRoomPricePerNight(room.getPricePerNight());
        calculationsExpected.setRoomSpecialPricePerNight(0);
        calculationsExpected.setRoomSpecialTotalPrice(0);
        calculationsExpected.setRoomTotalPrice(room.getPricePerNight() * calculationsExpected.getTotalNights());
        calculationsExpected.setBreakFastPricePerNight(4);
        calculationsExpected.setBreakFastTotalPrice(4);
        calculationsExpected.setCarParkingPricePerNight(10);
        calculationsExpected.setCarParkingTotalPrice(10);
        calculationsExpected.setSpaPricePerNight(5);
        calculationsExpected.setSpaTotalPrice(0);
        calculationsExpected.setLaundryPricePerNight(2);
        calculationsExpected.setLaundryTotalPrice(2);
        calculationsExpected.setShuttlePricePerNight(20.0);
        calculationsExpected.setShuttleTotalPrice(20.0);
        calculationsExpected.setCodeDiscountPrice(0);
        calculationsExpected.setTotalBookingPrice(336.0);

        when(roomsService.findById(room.getId())).thenReturn(room);

        // Act
        calculated = businessService.calculateTotalPriceBooking(book);

        // Asserts
        assertEquals(calculationsExpected.isLongStay(), calculated.isLongStay());
        assertEquals(calculationsExpected.totalNights, calculated.totalNights);
        assertEquals(calculationsExpected.roomPricePerNight, calculated.roomPricePerNight);
        assertEquals(calculationsExpected.roomTotalPrice, calculated.roomTotalPrice);
        assertEquals(calculationsExpected.roomSpecialPricePerNight, calculated.roomSpecialPricePerNight);
        assertEquals(calculationsExpected.roomSpecialTotalPrice, calculated.roomSpecialTotalPrice);
        assertEquals(calculationsExpected.breakFastPricePerNight, calculated.breakFastPricePerNight);
        assertEquals(calculationsExpected.breakFastTotalPrice, calculated.breakFastTotalPrice);
        assertEquals(calculationsExpected.carParkingPricePerNight, calculated.carParkingPricePerNight);
        assertEquals(calculationsExpected.carParkingTotalPrice, calculated.carParkingTotalPrice);
        assertEquals(calculationsExpected.spaPricePerNight, calculated.spaPricePerNight);
        assertEquals(calculationsExpected.spaTotalPrice, calculated.spaTotalPrice);
        assertEquals(calculationsExpected.laundryPricePerNight, calculated.laundryPricePerNight);
        assertEquals(calculationsExpected.laundryTotalPrice, calculated.laundryTotalPrice);
        assertEquals(calculationsExpected.shuttlePricePerNight, calculated.shuttlePricePerNight);
        assertEquals(calculationsExpected.shuttleTotalPrice, calculated.shuttleTotalPrice);
        assertEquals(calculationsExpected.codeDiscountPrice, calculated.codeDiscountPrice);
        assertEquals(calculationsExpected.totalBookingPrice, calculated.totalBookingPrice);

    }

    //SPA service with Individual Room
    @Test
    void GivenAnIndividualRoomWithBreakfastCarParkingLaundryShuttleSPA_ShouldBeReturnSumOfRoomBreakfastCarParkingLaundryShuttleSPAPrice() {
        // Arrange
        room.setId(5);
        room.setCode("");
        room.setPricePerNight(50.0);
        room.setDescription("description here");
        room.setGuests(1);
        room.setImage("image here");
        roomType.setRoomsById(new ArrayList<>());
        roomType.setId(RoomTypes.IndividualRoom.getRoomType());
        roomType.setName("room name");
        roomType.setDescription("description here");
        room.setRoomtypesByFkRoomtypeId(roomType);

        book.setRoomId(room.getId());
        book.setCheckIn("2021-01-01");
        book.setCheckOut("2021-01-02");
        book.setGuests(String.valueOf(room.getGuests()));
        book.setBreakfastService(true);
        book.setCarParkingService(true);
        book.setSpaService(true);
        book.setLaundryService(true);
        book.setShuttleService(true);
        book.setCodeDiscount("");

        calculationsExpected.setTotalNights(1);
        calculationsExpected.setLongStay(false);
        calculationsExpected.setRoomPricePerNight(room.getPricePerNight());
        calculationsExpected.setRoomSpecialPricePerNight(0);
        calculationsExpected.setRoomSpecialTotalPrice(0);
        calculationsExpected.setRoomTotalPrice(room.getPricePerNight() * calculationsExpected.getTotalNights());
        calculationsExpected.setBreakFastPricePerNight(4);
        calculationsExpected.setBreakFastTotalPrice(4);
        calculationsExpected.setCarParkingPricePerNight(10);
        calculationsExpected.setCarParkingTotalPrice(10);
        calculationsExpected.setSpaPricePerNight(5);
        calculationsExpected.setSpaTotalPrice(5);
        calculationsExpected.setLaundryPricePerNight(2);
        calculationsExpected.setLaundryTotalPrice(2);
        calculationsExpected.setShuttlePricePerNight(20.0);
        calculationsExpected.setShuttleTotalPrice(20.0);
        calculationsExpected.setCodeDiscountPrice(0);
        calculationsExpected.setTotalBookingPrice(91.0);

        when(roomsService.findById(room.getId())).thenReturn(room);

        // Act
        calculated = businessService.calculateTotalPriceBooking(book);

        // Asserts
        assertEquals(calculationsExpected.isLongStay(), calculated.isLongStay());
        assertEquals(calculationsExpected.totalNights, calculated.totalNights);
        assertEquals(calculationsExpected.roomPricePerNight, calculated.roomPricePerNight);
        assertEquals(calculationsExpected.roomTotalPrice, calculated.roomTotalPrice);
        assertEquals(calculationsExpected.roomSpecialPricePerNight, calculated.roomSpecialPricePerNight);
        assertEquals(calculationsExpected.roomSpecialTotalPrice, calculated.roomSpecialTotalPrice);
        assertEquals(calculationsExpected.breakFastPricePerNight, calculated.breakFastPricePerNight);
        assertEquals(calculationsExpected.breakFastTotalPrice, calculated.breakFastTotalPrice);
        assertEquals(calculationsExpected.carParkingPricePerNight, calculated.carParkingPricePerNight);
        assertEquals(calculationsExpected.carParkingTotalPrice, calculated.carParkingTotalPrice);
        assertEquals(calculationsExpected.spaPricePerNight, calculated.spaPricePerNight);
        assertEquals(calculationsExpected.spaTotalPrice, calculated.spaTotalPrice);
        assertEquals(calculationsExpected.laundryPricePerNight, calculated.laundryPricePerNight);
        assertEquals(calculationsExpected.laundryTotalPrice, calculated.laundryTotalPrice);
        assertEquals(calculationsExpected.shuttlePricePerNight, calculated.shuttlePricePerNight);
        assertEquals(calculationsExpected.shuttleTotalPrice, calculated.shuttleTotalPrice);
        assertEquals(calculationsExpected.codeDiscountPrice, calculated.codeDiscountPrice);
        assertEquals(calculationsExpected.totalBookingPrice, calculated.totalBookingPrice);

    }

    //SPA service with Familiy Room
    @Test
    void GivenAnFamilyRoomWithBreakfastCarParkingLaundryShuttleSPA_ShouldBeReturnSumOfRoomBreakfastCarParkingLaundryShuttleSPAPrice() {
        // Arrange
        room.setId(9);
        room.setCode("");
        room.setPricePerNight(155.0);
        room.setDescription("description here");
        room.setGuests(4);
        room.setImage("image here");
        roomType.setRoomsById(new ArrayList<>());
        roomType.setId(RoomTypes.FamilyRoom.getRoomType());
        roomType.setName("room name");
        roomType.setDescription("description here");
        room.setRoomtypesByFkRoomtypeId(roomType);

        book.setRoomId(room.getId());
        book.setCheckIn("2021-01-01");
        book.setCheckOut("2021-01-02");
        book.setGuests(String.valueOf(room.getGuests()));
        book.setBreakfastService(true);
        book.setCarParkingService(true);
        book.setSpaService(true);
        book.setLaundryService(true);
        book.setShuttleService(true);
        book.setCodeDiscount("");

        calculationsExpected.setTotalNights(1);
        calculationsExpected.setLongStay(false);
        calculationsExpected.setRoomPricePerNight(room.getPricePerNight());
        calculationsExpected.setRoomSpecialPricePerNight(0);
        calculationsExpected.setRoomSpecialTotalPrice(0);
        calculationsExpected.setRoomTotalPrice(room.getPricePerNight() * calculationsExpected.getTotalNights());
        calculationsExpected.setBreakFastPricePerNight(4);
        calculationsExpected.setBreakFastTotalPrice(4);
        calculationsExpected.setCarParkingPricePerNight(10);
        calculationsExpected.setCarParkingTotalPrice(10);
        calculationsExpected.setSpaPricePerNight(5);
        calculationsExpected.setSpaTotalPrice(5);
        calculationsExpected.setLaundryPricePerNight(2);
        calculationsExpected.setLaundryTotalPrice(2);
        calculationsExpected.setShuttlePricePerNight(20.0);
        calculationsExpected.setShuttleTotalPrice(20.0);
        calculationsExpected.setCodeDiscountPrice(0);
        calculationsExpected.setTotalBookingPrice(196.0);

        when(roomsService.findById(room.getId())).thenReturn(room);

        // Act
        calculated = businessService.calculateTotalPriceBooking(book);

        // Asserts
        assertEquals(calculationsExpected.isLongStay(), calculated.isLongStay());
        assertEquals(calculationsExpected.totalNights, calculated.totalNights);
        assertEquals(calculationsExpected.roomPricePerNight, calculated.roomPricePerNight);
        assertEquals(calculationsExpected.roomTotalPrice, calculated.roomTotalPrice);
        assertEquals(calculationsExpected.roomSpecialPricePerNight, calculated.roomSpecialPricePerNight);
        assertEquals(calculationsExpected.roomSpecialTotalPrice, calculated.roomSpecialTotalPrice);
        assertEquals(calculationsExpected.breakFastPricePerNight, calculated.breakFastPricePerNight);
        assertEquals(calculationsExpected.breakFastTotalPrice, calculated.breakFastTotalPrice);
        assertEquals(calculationsExpected.carParkingPricePerNight, calculated.carParkingPricePerNight);
        assertEquals(calculationsExpected.carParkingTotalPrice, calculated.carParkingTotalPrice);
        assertEquals(calculationsExpected.spaPricePerNight, calculated.spaPricePerNight);
        assertEquals(calculationsExpected.spaTotalPrice, calculated.spaTotalPrice);
        assertEquals(calculationsExpected.laundryPricePerNight, calculated.laundryPricePerNight);
        assertEquals(calculationsExpected.laundryTotalPrice, calculated.laundryTotalPrice);
        assertEquals(calculationsExpected.shuttlePricePerNight, calculated.shuttlePricePerNight);
        assertEquals(calculationsExpected.shuttleTotalPrice, calculated.shuttleTotalPrice);
        assertEquals(calculationsExpected.codeDiscountPrice, calculated.codeDiscountPrice);
        assertEquals(calculationsExpected.totalBookingPrice, calculated.totalBookingPrice);

    }

    //SPA is a service included in Luxury
    @Test
    void GivenALuxuryWithBreakfastCarParkingLaundryShuttleSPA_ShouldBeReturnSumOfRoomBreakfastCarParkingLaundryShuttlePrice_SPAServiceIsIncludedWithTheseRooms() {
        // Arrange
        room.setId(11);
        room.setCode("");
        room.setPricePerNight(450.0);
        room.setDescription("description here");
        room.setGuests(2);
        room.setImage("image here");
        roomType.setRoomsById(new ArrayList<>());
        roomType.setId(RoomTypes.LuxuryRoom.getRoomType());
        roomType.setName("room name");
        roomType.setDescription("description here");
        room.setRoomtypesByFkRoomtypeId(roomType);

        book.setRoomId(room.getId());
        book.setCheckIn("2021-01-01");
        book.setCheckOut("2021-01-02");
        book.setGuests(String.valueOf(room.getGuests()));
        book.setBreakfastService(true);
        book.setCarParkingService(true);
        book.setSpaService(true);
        book.setLaundryService(true);
        book.setShuttleService(true);
        book.setCodeDiscount("");

        calculationsExpected.setTotalNights(1);
        calculationsExpected.setLongStay(false);
        calculationsExpected.setRoomPricePerNight(room.getPricePerNight());
        calculationsExpected.setRoomSpecialPricePerNight(0);
        calculationsExpected.setRoomSpecialTotalPrice(0);
        calculationsExpected.setRoomTotalPrice(room.getPricePerNight() * calculationsExpected.getTotalNights());
        calculationsExpected.setBreakFastPricePerNight(4);
        calculationsExpected.setBreakFastTotalPrice(4);
        calculationsExpected.setCarParkingPricePerNight(10);
        calculationsExpected.setCarParkingTotalPrice(10);
        calculationsExpected.setSpaPricePerNight(5);
        calculationsExpected.setSpaTotalPrice(0);
        calculationsExpected.setLaundryPricePerNight(2);
        calculationsExpected.setLaundryTotalPrice(2);
        calculationsExpected.setShuttlePricePerNight(20.0);
        calculationsExpected.setShuttleTotalPrice(20.0);
        calculationsExpected.setCodeDiscountPrice(0);
        calculationsExpected.setTotalBookingPrice(486.0);

        when(roomsService.findById(room.getId())).thenReturn(room);

        // Act
        calculated = businessService.calculateTotalPriceBooking(book);

        // Asserts
        assertEquals(calculationsExpected.isLongStay(), calculated.isLongStay());
        assertEquals(calculationsExpected.totalNights, calculated.totalNights);
        assertEquals(calculationsExpected.roomPricePerNight, calculated.roomPricePerNight);
        assertEquals(calculationsExpected.roomTotalPrice, calculated.roomTotalPrice);
        assertEquals(calculationsExpected.roomSpecialPricePerNight, calculated.roomSpecialPricePerNight);
        assertEquals(calculationsExpected.roomSpecialTotalPrice, calculated.roomSpecialTotalPrice);
        assertEquals(calculationsExpected.breakFastPricePerNight, calculated.breakFastPricePerNight);
        assertEquals(calculationsExpected.breakFastTotalPrice, calculated.breakFastTotalPrice);
        assertEquals(calculationsExpected.carParkingPricePerNight, calculated.carParkingPricePerNight);
        assertEquals(calculationsExpected.carParkingTotalPrice, calculated.carParkingTotalPrice);
        assertEquals(calculationsExpected.spaPricePerNight, calculated.spaPricePerNight);
        assertEquals(calculationsExpected.spaTotalPrice, calculated.spaTotalPrice);
        assertEquals(calculationsExpected.laundryPricePerNight, calculated.laundryPricePerNight);
        assertEquals(calculationsExpected.laundryTotalPrice, calculated.laundryTotalPrice);
        assertEquals(calculationsExpected.shuttlePricePerNight, calculated.shuttlePricePerNight);
        assertEquals(calculationsExpected.shuttleTotalPrice, calculated.shuttleTotalPrice);
        assertEquals(calculationsExpected.codeDiscountPrice, calculated.codeDiscountPrice);
        assertEquals(calculationsExpected.totalBookingPrice, calculated.totalBookingPrice);

    }

    //SPA service with Double Room
    @Test
    void GivenAnDoubleRoomWithBreakfastCarParkingLaundryShuttleSPA_ShouldBeReturnSumOfRoomBreakfastCarParkingLaundryShuttleSPAPrice() {
        // Arrange
        room.setId(14);
        room.setCode("");
        room.setPricePerNight(80.0);
        room.setDescription("description here");
        room.setGuests(2);
        room.setImage("image here");
        roomType.setRoomsById(new ArrayList<>());
        roomType.setId(RoomTypes.DoubleRoom.getRoomType());
        roomType.setName("room name");
        roomType.setDescription("description here");
        room.setRoomtypesByFkRoomtypeId(roomType);

        book.setRoomId(room.getId());
        book.setCheckIn("2021-01-01");
        book.setCheckOut("2021-01-02");
        book.setGuests(String.valueOf(room.getGuests()));
        book.setBreakfastService(true);
        book.setCarParkingService(true);
        book.setSpaService(true);
        book.setLaundryService(true);
        book.setShuttleService(true);
        book.setCodeDiscount("");

        calculationsExpected.setTotalNights(1);
        calculationsExpected.setLongStay(false);
        calculationsExpected.setRoomPricePerNight(room.getPricePerNight());
        calculationsExpected.setRoomSpecialPricePerNight(0);
        calculationsExpected.setRoomSpecialTotalPrice(0);
        calculationsExpected.setRoomTotalPrice(room.getPricePerNight() * calculationsExpected.getTotalNights());
        calculationsExpected.setBreakFastPricePerNight(4);
        calculationsExpected.setBreakFastTotalPrice(4);
        calculationsExpected.setCarParkingPricePerNight(10);
        calculationsExpected.setCarParkingTotalPrice(10);
        calculationsExpected.setSpaPricePerNight(5);
        calculationsExpected.setSpaTotalPrice(5);
        calculationsExpected.setLaundryPricePerNight(2);
        calculationsExpected.setLaundryTotalPrice(2);
        calculationsExpected.setShuttlePricePerNight(20.0);
        calculationsExpected.setShuttleTotalPrice(20.0);
        calculationsExpected.setCodeDiscountPrice(0);
        calculationsExpected.setTotalBookingPrice(121.0);

        when(roomsService.findById(room.getId())).thenReturn(room);

        // Act
        calculated = businessService.calculateTotalPriceBooking(book);

        // Asserts
        assertEquals(calculationsExpected.isLongStay(), calculated.isLongStay());
        assertEquals(calculationsExpected.totalNights, calculated.totalNights);
        assertEquals(calculationsExpected.roomPricePerNight, calculated.roomPricePerNight);
        assertEquals(calculationsExpected.roomTotalPrice, calculated.roomTotalPrice);
        assertEquals(calculationsExpected.roomSpecialPricePerNight, calculated.roomSpecialPricePerNight);
        assertEquals(calculationsExpected.roomSpecialTotalPrice, calculated.roomSpecialTotalPrice);
        assertEquals(calculationsExpected.breakFastPricePerNight, calculated.breakFastPricePerNight);
        assertEquals(calculationsExpected.breakFastTotalPrice, calculated.breakFastTotalPrice);
        assertEquals(calculationsExpected.carParkingPricePerNight, calculated.carParkingPricePerNight);
        assertEquals(calculationsExpected.carParkingTotalPrice, calculated.carParkingTotalPrice);
        assertEquals(calculationsExpected.spaPricePerNight, calculated.spaPricePerNight);
        assertEquals(calculationsExpected.spaTotalPrice, calculated.spaTotalPrice);
        assertEquals(calculationsExpected.laundryPricePerNight, calculated.laundryPricePerNight);
        assertEquals(calculationsExpected.laundryTotalPrice, calculated.laundryTotalPrice);
        assertEquals(calculationsExpected.shuttlePricePerNight, calculated.shuttlePricePerNight);
        assertEquals(calculationsExpected.shuttleTotalPrice, calculated.shuttleTotalPrice);
        assertEquals(calculationsExpected.codeDiscountPrice, calculated.codeDiscountPrice);
        assertEquals(calculationsExpected.totalBookingPrice, calculated.totalBookingPrice);

    }

    //SPA service Special Case if Room Id is Unknown
    @Test
    void GivenAnUnknownRoomWithSPA_ShouldBeReturnSumOfRoomSPAPrice() {
        // Arrange
        room.setId(5);
        room.setCode("");
        room.setPricePerNight(50.0);
        room.setDescription("description here");
        room.setGuests(1);
        room.setImage("image here");
        roomType.setRoomsById(new ArrayList<>());
        roomType.setId(RoomTypes.UnkwnowRoom.getRoomType());
        roomType.setName("room name");
        roomType.setDescription("description here");
        room.setRoomtypesByFkRoomtypeId(roomType);

        book.setRoomId(room.getId());
        book.setCheckIn("2021-01-01");
        book.setCheckOut("2021-01-02");
        book.setGuests(String.valueOf(room.getGuests()));
        book.setBreakfastService(false);
        book.setCarParkingService(false);
        book.setSpaService(true);
        book.setLaundryService(false);
        book.setShuttleService(false);
        book.setCodeDiscount("");

        calculationsExpected.setTotalNights(1);
        calculationsExpected.setLongStay(false);
        calculationsExpected.setRoomPricePerNight(room.getPricePerNight());
        calculationsExpected.setRoomSpecialPricePerNight(0);
        calculationsExpected.setRoomSpecialTotalPrice(0);
        calculationsExpected.setRoomTotalPrice(room.getPricePerNight() * calculationsExpected.getTotalNights());
        calculationsExpected.setBreakFastPricePerNight(0);
        calculationsExpected.setBreakFastTotalPrice(0);
        calculationsExpected.setCarParkingPricePerNight(0);
        calculationsExpected.setCarParkingTotalPrice(0);
        calculationsExpected.setSpaPricePerNight(5);
        calculationsExpected.setSpaTotalPrice(5);
        calculationsExpected.setLaundryPricePerNight(0);
        calculationsExpected.setLaundryTotalPrice(0);
        calculationsExpected.setShuttlePricePerNight(0);
        calculationsExpected.setShuttleTotalPrice(0);
        calculationsExpected.setCodeDiscountPrice(0);
        calculationsExpected.setTotalBookingPrice(55.0);

        when(roomsService.findById(room.getId())).thenReturn(room);

        // Act
        calculated = businessService.calculateTotalPriceBooking(book);

        // Asserts
        assertEquals(calculationsExpected.isLongStay(), calculated.isLongStay());
        assertEquals(calculationsExpected.totalNights, calculated.totalNights);
        assertEquals(calculationsExpected.roomPricePerNight, calculated.roomPricePerNight);
        assertEquals(calculationsExpected.roomTotalPrice, calculated.roomTotalPrice);
        assertEquals(calculationsExpected.roomSpecialPricePerNight, calculated.roomSpecialPricePerNight);
        assertEquals(calculationsExpected.roomSpecialTotalPrice, calculated.roomSpecialTotalPrice);
        assertEquals(calculationsExpected.breakFastPricePerNight, calculated.breakFastPricePerNight);
        assertEquals(calculationsExpected.breakFastTotalPrice, calculated.breakFastTotalPrice);
        assertEquals(calculationsExpected.carParkingPricePerNight, calculated.carParkingPricePerNight);
        assertEquals(calculationsExpected.carParkingTotalPrice, calculated.carParkingTotalPrice);
        assertEquals(calculationsExpected.spaPricePerNight, calculated.spaPricePerNight);
        assertEquals(calculationsExpected.spaTotalPrice, calculated.spaTotalPrice);
        assertEquals(calculationsExpected.laundryPricePerNight, calculated.laundryPricePerNight);
        assertEquals(calculationsExpected.laundryTotalPrice, calculated.laundryTotalPrice);
        assertEquals(calculationsExpected.shuttlePricePerNight, calculated.shuttlePricePerNight);
        assertEquals(calculationsExpected.shuttleTotalPrice, calculated.shuttleTotalPrice);
        assertEquals(calculationsExpected.codeDiscountPrice, calculated.codeDiscountPrice);
        assertEquals(calculationsExpected.totalBookingPrice, calculated.totalBookingPrice);

    }

    // Stay greater than or equal 20 nights 20% discount on room price
    @Test
    void GivenAnyRoomWithAStayGreaterThanOrEqualTo20NightsRoomPrice_ShouldBeApply20percentOfDiscountOnRoomPricePerNight(){
        // Arrange
        room.setId(5);
        room.setCode("");
        room.setPricePerNight(50.0);
        room.setDescription("description here");
        room.setGuests(1);
        room.setImage("image here");
        roomType.setRoomsById(new ArrayList<>());
        roomType.setId(RoomTypes.IndividualRoom.getRoomType());
        roomType.setName("room name");
        roomType.setDescription("description here");
        room.setRoomtypesByFkRoomtypeId(roomType);

        book.setRoomId(room.getId());
        book.setCheckIn("2021-01-01");
        book.setCheckOut("2021-01-21");
        book.setGuests(String.valueOf(room.getGuests()));
        book.setBreakfastService(false);
        book.setCarParkingService(false);
        book.setSpaService(false);
        book.setLaundryService(false);
        book.setShuttleService(false);
        book.setCodeDiscount("");

        calculationsExpected.setTotalNights(20);
        calculationsExpected.setRoomPricePerNight(room.getPricePerNight());
        calculationsExpected.setRoomSpecialPricePerNight(40.0); // 50 - 20% = 40.0
        calculationsExpected.setRoomSpecialTotalPrice(800.0);
        calculationsExpected.setRoomTotalPrice(room.getPricePerNight() * calculationsExpected.getTotalNights());
        calculationsExpected.setBreakFastPricePerNight(0);
        calculationsExpected.setBreakFastTotalPrice(0);
        calculationsExpected.setCarParkingPricePerNight(0);
        calculationsExpected.setCarParkingTotalPrice(0);
        calculationsExpected.setSpaPricePerNight(0);
        calculationsExpected.setSpaTotalPrice(0);
        calculationsExpected.setLaundryPricePerNight(0);
        calculationsExpected.setLaundryTotalPrice(0);
        calculationsExpected.setShuttlePricePerNight(0);
        calculationsExpected.setShuttleTotalPrice(0);
        calculationsExpected.setCodeDiscountPrice(0);
        calculationsExpected.setTotalBookingPrice(800.0);

        when(roomsService.findById(room.getId())).thenReturn(room);

        // Act
        calculated = businessService.calculateTotalPriceBooking(book);

        // Asserts
        assertEquals(calculationsExpected.totalNights, calculated.totalNights);
        assertEquals(calculationsExpected.roomPricePerNight, calculated.roomPricePerNight);
        assertEquals(calculationsExpected.roomTotalPrice, calculated.roomTotalPrice);
        assertEquals(calculationsExpected.roomSpecialPricePerNight, calculated.roomSpecialPricePerNight);
        assertEquals(calculationsExpected.roomSpecialTotalPrice, calculated.roomSpecialTotalPrice);
        assertEquals(calculationsExpected.breakFastPricePerNight, calculated.breakFastPricePerNight);
        assertEquals(calculationsExpected.breakFastTotalPrice, calculated.breakFastTotalPrice);
        assertEquals(calculationsExpected.carParkingPricePerNight, calculated.carParkingPricePerNight);
        assertEquals(calculationsExpected.carParkingTotalPrice, calculated.carParkingTotalPrice);
        assertEquals(calculationsExpected.spaPricePerNight, calculated.spaPricePerNight);
        assertEquals(calculationsExpected.spaTotalPrice, calculated.spaTotalPrice);
        assertEquals(calculationsExpected.laundryPricePerNight, calculated.laundryPricePerNight);
        assertEquals(calculationsExpected.laundryTotalPrice, calculated.laundryTotalPrice);
        assertEquals(calculationsExpected.shuttlePricePerNight, calculated.shuttlePricePerNight);
        assertEquals(calculationsExpected.shuttleTotalPrice, calculated.shuttleTotalPrice);
        assertEquals(calculationsExpected.codeDiscountPrice, calculated.codeDiscountPrice);
        assertEquals(calculationsExpected.totalBookingPrice, calculated.totalBookingPrice);

    }

}