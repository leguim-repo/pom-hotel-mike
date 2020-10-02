package com.pomhotel.booking.application.repositories;

import com.pomhotel.booking.application.domain.entities.RoomtypesEntity;

import java.util.List;

//--- Repository Interface -------------------------------------------
public interface RoomtypesRepository {

    RoomtypesEntity findById(long id);

    List<RoomtypesEntity> findAll();

    void saveOrUpdate(RoomtypesEntity entity);

    void deleteById(long id);

    void delete(RoomtypesEntity entity);

}
