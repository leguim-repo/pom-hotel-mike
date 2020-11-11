package com.pomhotel.booking.ui.api.controllers;

import com.jayway.jsonpath.JsonPath;
import org.apache.commons.validator.UrlValidator;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;

import javax.validation.constraints.AssertTrue;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// con random port me peta
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = MusicApiController.class)
@SpringBootTest(classes = MusicApiController.class)
@AutoConfigureMockMvc
class MusicApiControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Before("")
    public void setup(){
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    public void ShouldReturnOkWhenVisitMusic() throws  Exception{
        mvc.perform(get("/api/music").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void CheckIfReturnedIsOkAndJSONObjectContainsAKeyNamedLinkAndNotIsEmpty() throws  Exception{

        mvc.perform(get("/api/music").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.link").exists())
                .andExpect(jsonPath("$.link").isNotEmpty())
        ;
    }

    @Test
    public void CheckIfMusicReturnAValidURL() throws Exception {
        MvcResult mvcResponse = mvc.perform(get("/api/music")
                                    .contentType(MediaType.APPLICATION_JSON))
                                    .andDo(print())
                                    .andReturn();

        String link = JsonPath.read(mvcResponse.getResponse().getContentAsString(), "$.link");

        assertTrue(link.contains("https://youtu.be/"));

    }
}