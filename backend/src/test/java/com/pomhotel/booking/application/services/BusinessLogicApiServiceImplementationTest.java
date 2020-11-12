package com.pomhotel.booking.application.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


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
}