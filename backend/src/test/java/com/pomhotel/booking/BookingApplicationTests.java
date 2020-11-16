package com.pomhotel.booking;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//Al pasar BookingApplication.class pasamos el contexto a todas las clases que descienden de ella (en nuestro caso toda la app)
@SpringBootTest(classes=BookingApplication.class, webEnvironment =SpringBootTest.WebEnvironment.RANDOM_PORT )
class BookingApplicationTests {

	@Test
	void contextLoads() {
	}

}
