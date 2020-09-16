package com.pomhotel.booking.application.factories;

import com.pomhotel.booking.application.domain.entities.RoomtypesEntity;
import com.pomhotel.booking.application.models.RoomTypeModel;
import org.springframework.stereotype.Component;

@Component
public class RoomtypesFactory {
    public RoomtypesEntity createEntity(RoomTypeModel model){
        RoomtypesEntity entity = new RoomtypesEntity();
        entity.setId(model.id);
        entity.setName(model.name);
        entity.setDescription(model.description);
        entity.setPreferencesById(model.preferencesById);
        entity.setRoomsById(model.roomsById);
        return entity;
    }

    public RoomTypeModel createModel(RoomtypesEntity entity){
        RoomTypeModel model = new RoomTypeModel();
        model.id = entity.getId();
        model.name = entity.getName();
        model.description = entity.getDescription();
        model.preferencesById = entity.getPreferencesById();
        model.roomsById = entity.getRoomsById();
        return model;
    }
}
