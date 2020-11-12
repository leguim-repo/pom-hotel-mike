package com.pomhotel.booking.application.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class BusinessLogicApiServiceImplementationTest {

    @Autowired
    private BusinessLogicApiServiceImplementation mockService;

    @Test
    @DisplayName("El Service no es Null")
    public void ShouldInnjectedBeanNeverShouldBeNull()
    {
        assertThat(mockService.dummyFunction()).isNull();
    }

    @Test
    @DisplayName("Test dummyFunction del servicio")
    void dummyFunction() {
        assertThat(mockService).isNotNull();
    }

    @Test
    void calculateBasePrice() {
        assertEquals(4,mockService.calculateBasePrice(2,2, Date.valueOf("2020-01-01")));
    }

    @Test
    void calculateBreakFastService() {
        assertEquals(6,mockService.calculateBasePrice(2,3, Date.valueOf("2020-01-01")));

    }
}