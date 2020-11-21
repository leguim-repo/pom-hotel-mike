package com.pomhotel.booking.ui.api.v1.controllers;

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
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = BookingApplication.class)
class HomeApiControllerTest_MockBean {
    @MockBean
    private RoomsService mockRoomService;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private GetRoomByIdController homeApiController;

    @Before("")
    public void setup(){
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();

    }

    @Test
    @DisplayName("MockBean Endpoint: \"/api/roomdetail/{targetId}\"")
    void GetFindRoomByIdApi() {
        RoomsModel fakeRoomsModel = new RoomsModel();
        fakeRoomsModel.setId(1);
        fakeRoomsModel.setPricePerNight(200.00);

        when(mockRoomService.findById(1)).thenReturn(fakeRoomsModel);
        assertEquals(homeApiController.getRoomById("1"), fakeRoomsModel);

    }

    @Test
    @DisplayName("MockBean Endpoint: \"/api/v1/getroombyid/{targetId}\"")
    void findRoomByIdApi_mockMvc() throws Exception {
        RoomsModel theRoom = new RoomsModel();
        theRoom.setId(1);
        theRoom.setPricePerNight(300.00);
        theRoom.setCode("SU1");
        when(mockRoomService.findById(1)).thenReturn(theRoom);
        this.mockMvc.perform(get("/api/v1/getroombyid/1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pricePerNight").value(300.00))
                .andExpect(jsonPath("$.code").value("SU1"));
    }
}