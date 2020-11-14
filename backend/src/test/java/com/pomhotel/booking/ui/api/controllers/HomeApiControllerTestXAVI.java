package com.pomhotel.booking.ui.api.controllers;

import com.pomhotel.booking.BookingApplication;
import com.pomhotel.booking.application.models.RoomsModel;
import com.pomhotel.booking.application.services.RoomsService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
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
    @DisplayName("Test usando MockBean")
    //@Disabled("Disabled because this test crash for unknown reasons")
    // https://github.com/leguim-repo/pom-hotel-mike/blob/ec2a4e988865e198f80e6a9c6844b170c18358e0/backend/src/test/java/com/pomhotel/booking/ui/api/controllers/HomeApiControllerTestXAVI.java
    void findRoomByIdApi_mockMvc() throws Exception {
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