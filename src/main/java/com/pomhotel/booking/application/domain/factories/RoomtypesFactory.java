package com.pomhotel.booking.application.domain.factories;

import com.pomhotel.booking.application.domain.entities.RoomtypesEntity;
import com.pomhotel.booking.application.models.RoomtypesModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoomtypesFactory {

    @Autowired
    public RoomtypesFactory() {
    }

    public RoomtypesEntity createEntity(RoomtypesModel model){
        RoomtypesEntity entity = new RoomtypesEntity();
        entity.setId(model.id);
        entity.setName(model.name);
        entity.setDescription(model.description);
        return entity;
    }

    public RoomtypesModel createModel(RoomtypesEntity entity){
        RoomtypesModel model = new RoomtypesModel();
        model.id = entity.getId();
        model.name = entity.getName();
        model.description = entity.getDescription();
        return model;
    }
}
