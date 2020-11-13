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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


// https://dzone.com/articles/rest-endpoint-testing-with-mockmvc

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = BookingApplication.class)
class HomeApiControllerTestXAVI {
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
    @DisplayName("Test usando Mockito sobre la api findRoomByIdApi")
    void findRoomByIdApi() {
        // Mock del Modelo
        RoomsModel fakeRoomsModel = new RoomsModel();
        fakeRoomsModel.setId(1);
        fakeRoomsModel.setPricePerNight(200.00);
        // Mock del service a testear
        //var mockRoomsService = Mockito.mock(RoomsService.class);
        //Mockito.when(mockRoomsService.findById(1)).thenReturn(fakeRoomsModel);
        //Test sobre la Api
        //HomeApiController testHomeApiController = new HomeApiController(mockRoomsService);
        //assertEquals(testHomeApiController.findRoomByIdApi("1"), fakeRoomsModel);
        when(mockRoomService.findById(1)).thenReturn(fakeRoomsModel);
        assertEquals(homeApiController.findRoomByIdApi("1"), fakeRoomsModel);

    }

    @Test
    @DisplayName("Test usando mockMvc sobre la api findRoomByIdApi")
    void findRoomByIdApi_mockMvc() throws Exception {
        this.mockMvc.perform(get("/api/roomdetail/1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                //.andExpect(jsonPath("$.pricePerNight").value(300.00))
                .andExpect(jsonPath("$.code").value("SU1"));



        /*
        this.mockMvc.perform(get("/api/roomdetail/2").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pricePerNight").value(320.00))
                .andExpect(jsonPath("$.code").value("SU2"));

        this.mockMvc.perform(get("/api/roomdetail/3").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pricePerNight").value(290.00))
                .andExpect(jsonPath("$.code").value("SU3"));

        this.mockMvc.perform(get("/api/roomdetail/4").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pricePerNight").value(285.00))
                .andExpect(jsonPath("$.code").value("SU4"));
        */

    }
}