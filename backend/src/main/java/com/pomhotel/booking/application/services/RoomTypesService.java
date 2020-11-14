package com.pomhotel.booking.application.services;

import com.pomhotel.booking.application.models.RoomTypesModel;
import java.util.List;

//--- Service Interface -------------------------------------------
public interface RoomTypesService {

    RoomTypesModel findById(long id);

    List<RoomTypesModel> findAll();

    void saveOrUpdate(RoomTypesModel model);

    void deleteById(long id);

    void delete(RoomTypesModel model);

}
