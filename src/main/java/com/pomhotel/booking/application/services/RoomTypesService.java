package com.pomhotel.booking.application.services;

import com.pomhotel.booking.application.models.RoomtypesModel;

import java.util.List;

public interface RoomTypesService {
    RoomtypesModel findById(long id);
    List<RoomtypesModel> findAll();
    void saveOrUpdate(RoomtypesModel model);
    void deleteById(long id);
    void delete(RoomtypesModel model);
}
