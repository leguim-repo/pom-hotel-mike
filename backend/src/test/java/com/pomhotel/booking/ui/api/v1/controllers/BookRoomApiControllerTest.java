package com.pomhotel.booking.ui.api.v1.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pomhotel.booking.BookingApplication;
import com.pomhotel.booking.application.models.ClientsModel;
import com.pomhotel.booking.application.models.RoomTypesModel;
import com.pomhotel.booking.application.models.RoomsModel;
import com.pomhotel.booking.application.services.BusinessLogicApiService;
import com.pomhotel.booking.application.services.ClientLoginService;
import com.pomhotel.booking.application.services.RoomsService;
import com.pomhotel.booking.ui.api.v1.dto.BookingApiDTO;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = BookingApplication.class)
class BookRoomApiControllerTest {

    BookingApiDTO book;
    RoomsModel room;
    RoomTypesModel roomType;
    ClientsModel clientFake;

    @MockBean
    RoomsService roomsService;
    @MockBean
    ClientLoginService clientsService;


    @Autowired
    BusinessLogicApiService businessLogicService;


    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private GetAllRoomsController homeApiController;

    @Before("")
    public void setup(){
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();

    }
    @BeforeEach
    void setUp() {
        book = new BookingApiDTO();
        room = new RoomsModel();
        roomType = new RoomTypesModel();
        clientFake = new ClientsModel();

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

        clientFake.setId(1);
        clientFake.setName("miguel");
        clientFake.setEmail("email@fake.com");
        clientFake.setBookingsById(new ArrayList<>());

    }

    @Test
    @DisplayName("Endpoint: \"/api/bookings")
    @Disabled("Only used for test purposes. Not used in frontend")
    void getAllBookingsApi() {
    }

    @Test
    @DisplayName("Endpoint: \"/api/booking/{targetId}")
    @Disabled("pending to finish")
    void getBookingsByIdApi() throws Exception {
        when(roomsService.findById(room.getId())).thenReturn(room);

        mvc.perform(get("/api/booking/112221")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                 ;
    }

    @Test
    @DisplayName("Endpoint: \"/api/v1/getallreserveddatesbyroomid/{targetId}")
    void getAllBookingsDatesByRoomIdApi() throws Exception {
        MvcResult result;
        result = mvc.perform(get("/api/v1/getallreserveddatesbyroomid/1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                ;
        // this part is base in:
        // https://stackoverflow.com/questions/46885972/mockmvc-in-junit-tests-checking-result-for-listobject-and-mapenum-object
        ObjectMapper mapper = new ObjectMapper();
        List<Date> bookedDates = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<Date>>() {});
        //System.out.println(bookedDates);
        assertNotNull(bookedDates);
    }

    @Test
    @DisplayName("Endpoint: \"/api/v1/calculatepriceofbook")
    void calculatePriceOfBook() throws Exception {
        when(roomsService.findById(room.getId())).thenReturn(room);
        book.setCodeDiscount("CODE50");

        mvc.perform(post("/api/v1/calculatepriceofbook")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.toJson(book)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalBookingPrice").value(2950))
                .andExpect(jsonPath("$.codeDiscountPrice").value(-50))
                ;
    }

    @Test
    @DisplayName("Endpoint: \"/api/v1/bookingroomnow")
    void bookRoomNowApi() throws Exception {
        when(roomsService.findById(room.getId())).thenReturn(room);
        when(clientsService.findClientByUsername(clientFake.getName())).thenReturn(clientFake);

        mvc.perform(post("/api/v1/bookingroomnow")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.toJson(book)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookNowResult").value(true))
                ;
    }

    static byte[] toJson(Object object ) throws  Exception
    {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();
        return gson.toJson(object).getBytes();
    }
}