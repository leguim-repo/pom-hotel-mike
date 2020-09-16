package com.pomhotel.booking.application.repositories;

import com.pomhotel.booking.application.domain.entities.RoomsEntity;
import java.util.List;

public interface RoomsRepository {
    RoomsEntity findById(long id);
    List<RoomsEntity> findAll();
    void createNew(RoomsEntity entity);
    void update(RoomsEntity entity);
    void deleteById(long id);
    void delete(RoomsEntity entity);
}
