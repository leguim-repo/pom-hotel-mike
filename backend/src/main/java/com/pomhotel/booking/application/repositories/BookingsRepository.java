package com.pomhotel.booking.application.repositories;

import com.pomhotel.booking.application.domain.entities.BookingsEntity;
import com.pomhotel.booking.application.models.BookingDatesModel;

import java.util.List;

//--- Repository Interface -------------------------------------------
public interface BookingsRepository {

    BookingsEntity findById(long id);

    List<BookingsEntity> findAll();
    List<BookingDatesModel> prueba(long id);

    void saveOrUpdate(BookingsEntity entity);

    void deleteById(long id);

    void delete(BookingsEntity entity);

}
