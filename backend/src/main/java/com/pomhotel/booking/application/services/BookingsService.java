package com.pomhotel.booking.application.services;

import com.pomhotel.booking.application.models.BookingDatesModel;
import com.pomhotel.booking.application.models.BookingsModel;

import java.lang.reflect.Array;
import java.sql.Date;
import java.util.List;

//--- Service Interface -------------------------------------------
public interface BookingsService {

    BookingsModel findById(long id);

    List<BookingsModel> findAll();
    List<BookingDatesModel> prueba(long id);



    void saveOrUpdate(BookingsModel model);

    void deleteById(long id);

    void delete(BookingsModel model);

}
