package com.pomhotel.booking.application.repositories;

import com.pomhotel.booking.application.domain.entities.BookingsEntity;

import java.util.List;

public interface BookingsRepository {
    BookingsEntity findById(long id);
    List<BookingsEntity> findAll();
    void createNew(BookingsEntity entity);
    void update(BookingsEntity entity);
    void deleteById(long id);
    void delete(BookingsEntity entity);
}
