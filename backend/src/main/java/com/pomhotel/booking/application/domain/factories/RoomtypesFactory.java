package com.pomhotel.booking.application.domain.factories;

import com.pomhotel.booking.application.domain.entities.RoomtypesEntity;
import com.pomhotel.booking.application.models.RoomTypesModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//--- Factory -------------------------------------------------------
@Component
public class RoomtypesFactory {

    //--- Constructor -----------------------------------------------
    @Autowired
    public RoomtypesFactory() {
    }

    //--- Functions -------------------------------------------------
    public RoomtypesEntity createEntity(RoomTypesModel model){
        RoomtypesEntity entity = new RoomtypesEntity();
        entity.setId(model.id);
        entity.setName(model.name);
        entity.setDescription(model.description);
        return entity;
    }

    public RoomTypesModel createModel(RoomtypesEntity entity){
        RoomTypesModel model = new RoomTypesModel();
        model.id = entity.getId();
        model.name = entity.getName();
        model.description = entity.getDescription();
        return model;
    }
}
