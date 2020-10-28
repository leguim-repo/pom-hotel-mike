package com.pomhotel.booking.ui.api;

import com.pomhotel.booking.application.models.RoomsModel;
import com.pomhotel.booking.application.services.*;
import com.pomhotel.booking.ui.controllers.SecurityController;
import com.pomhotel.booking.ui.servicies.BookingLogicalService;
import com.pomhotel.booking.ui.servicies.BookingLogicalServiceImplementation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


// https://dzone.com/articles/rest-endpoint-testing-with-mockmvc

@SpringBootTest
@AutoConfigureMockMvc
class HomeApiControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Test usando Mockito sobre la api findRoomByIdApi")
    void findRoomByIdApi() {
        RoomsModel fakeRoomsModel = new RoomsModel();
        fakeRoomsModel.setId(1);
        fakeRoomsModel.setPricePerNight(200.00);
        var mockRoomsService = Mockito.mock(RoomsService.class);
        Mockito.when(mockRoomsService.findById(1)).thenReturn(fakeRoomsModel);
        HomeApiController testHomeApiController = new HomeApiController(mockRoomsService,Mockito.mock(SecurityController.class));
        assertEquals(testHomeApiController.findRoomByIdApi((long) 1), fakeRoomsModel);

    }

    @Test
    @DisplayName("Test usando mockMvc sobre la api findRoomByIdApi")
    void findRoomByIdApi_mockMvc() throws Exception {
        this.mockMvc.perform(get("/api/roomdetail/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pricePerNight").value(300.00))
                .andExpect(jsonPath("$.code").value("SU1"));

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


    }




}