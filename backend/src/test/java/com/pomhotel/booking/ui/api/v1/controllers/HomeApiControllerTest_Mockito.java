package com.pomhotel.booking.ui.api.v1.controllers;

import com.pomhotel.booking.application.models.RoomsModel;
import com.pomhotel.booking.application.services.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


// https://dzone.com/articles/rest-endpoint-testing-with-mockmvc

@AutoConfigureMockMvc
@SpringBootTest
class HomeApiControllerTest_Mockito {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GetRoomByIdController homeApiController;
    private RoomsService mockRoomService;

    @Test
    @DisplayName("Mockito Endpoint: \"/api/roomdetail/{targetId}\"")
    void findRoomByIdApi() {
        // Mock del Modelo
        RoomsModel fakeRoomsModel = new RoomsModel();
        fakeRoomsModel.setId(1);
        fakeRoomsModel.setPricePerNight(200.00);
        // Mock del service a testear
        var mockRoomsService = Mockito.mock(RoomsService.class);
        Mockito.when(mockRoomsService.findById(1)).thenReturn(fakeRoomsModel);
        //Test sobre la Api
        GetRoomByIdController testHomeApiController = new GetRoomByIdController(mockRoomsService);
        assertEquals(testHomeApiController.getRoomById("1"), fakeRoomsModel);

    }

    @Test
    @DisplayName("MockMvc Endpoint: \"/api/v1/getroombyid/{targetId}\"")
    // De esta forma se hace un test hasta la BD
    void findRoomByIdApi_mockMvc() throws Exception {
        this.mockMvc.perform(get("/api/v1/getroombyid/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pricePerNight").value(300.00))
                .andExpect(jsonPath("$.code").value("SU1"));

        this.mockMvc.perform(get("/api/v1/getroombyid/2").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pricePerNight").value(320.00))
                .andExpect(jsonPath("$.code").value("SU2"));

        this.mockMvc.perform(get("/api/v1/getroombyid/3").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pricePerNight").value(290.00))
                .andExpect(jsonPath("$.code").value("SU3"));

        this.mockMvc.perform(get("/api/v1/getroombyid/4").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pricePerNight").value(285.00))
                .andExpect(jsonPath("$.code").value("SU4"));

    }
}