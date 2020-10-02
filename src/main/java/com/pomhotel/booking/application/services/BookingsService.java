package com.pomhotel.booking.application.services;

import com.pomhotel.booking.application.models.BookingsModel;

import java.util.List;

//--- Service Interface -------------------------------------------
public interface BookingsService {

    BookingsModel findById(long id);

    List<BookingsModel> findAll();

    void saveOrUpdate(BookingsModel model);

    void deleteById(long id);

    void delete(BookingsModel model);

}
