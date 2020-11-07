package com.pomhotel.booking.application.repositories;

import com.pomhotel.booking.application.domain.entities.BookingsEntity;
import com.pomhotel.booking.application.models.BookingDatesModel;

import java.lang.reflect.Array;
import java.sql.Date;
import java.util.List;

//--- Repository Interface -------------------------------------------
public interface BookingsRepository {

    BookingsEntity findById(long id);

    List<BookingsEntity> findAll();
    List<Object[]> getBookedDatesByRoomId(long id);

    void saveOrUpdate(BookingsEntity entity);

    void deleteById(long id);

    void delete(BookingsEntity entity);

}
