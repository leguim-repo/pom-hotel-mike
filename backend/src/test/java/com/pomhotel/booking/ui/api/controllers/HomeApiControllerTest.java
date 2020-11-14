package com.pomhotel.booking.ui.api.controllers;

import com.pomhotel.booking.BookingApplication;
import com.pomhotel.booking.application.models.RoomsModel;
import com.pomhotel.booking.application.services.RoomsService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = BookingApplication.class)
class HomeApiControllerTest {
    @MockBean
    private RoomsService mockRoomService;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private HomeApiController homeApiController;

    @Before("")
    public void setup(){
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();

    }

    @Test
    @DisplayName("Endpoint: \"/api/rooms")
    void getAllRoomsApi() {
    }

    @Test
    @DisplayName("Endpoint: \"/api/roomsandbookeddates")
    void getAllRoomsWithBookedDates() {
    }

    @Test
    @DisplayName("Endpoint: \"/api/roomdetail/{targetId}\"")
    void findRoomByIdApi() throws Exception {
        RoomsModel theRoom = new RoomsModel();
        theRoom.setId(1);
        theRoom.setPricePerNight(300.00);
        theRoom.setCode("SU1");
        when(mockRoomService.findById(1)).thenReturn(theRoom);
        this.mockMvc.perform(get("/api/roomdetail/1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pricePerNight").value(300.00))
                .andExpect(jsonPath("$.code").value("SU1"));
    }
}