package com.pomhotel.booking.application.services;

import com.pomhotel.booking.application.models.BookingDatesModel;
import com.pomhotel.booking.application.models.BookingsModel;
import com.pomhotel.booking.ui.api.dto.BookingApiDTO;

import java.lang.reflect.Array;
import java.sql.Date;
import java.util.List;

//--- Service Interface -------------------------------------------
public interface BookingsService {

    BookingsModel findById(long id);

    List<BookingsModel> findAll();
    List<BookingDatesModel> getBookedDatesByRoomId(long id);

    List<Date> generateBookedDatesInRunTime(List<BookingDatesModel> model);

    Integer SaveNewBooking(BookingApiDTO dto);

    void saveOrUpdate(BookingsModel model);
    Integer save(BookingsModel model);

    void deleteById(long id);

    void delete(BookingsModel model);

}
