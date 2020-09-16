package com.pomhotel.booking.application.repositories;

import com.pomhotel.booking.application.domain.entities.ClientsEntity;
import com.pomhotel.booking.application.domain.entities.LoginsEntity;
import com.pomhotel.booking.application.domain.entities.RoomtypesEntity;

import java.util.List;

public interface RoomtypesRepository {
    RoomtypesEntity findById(long id);
    List<RoomtypesEntity> findAll();
    void createNew(RoomtypesEntity entity);
    void update(RoomtypesEntity entity);
    void deleteById(long id);
    void delete(RoomtypesEntity entity);
}
