package com.pomhotel.booking.application.factories;

import com.pomhotel.booking.application.domain.entities.RoomsEntity;
import com.pomhotel.booking.application.models.RoomModel;
import org.springframework.stereotype.Component;

@Component
public class RoomsFactory {
    public RoomsEntity createEntity(RoomModel model){
        RoomsEntity entity = new RoomsEntity();
        entity.setId(model.id);
        entity.setCode(model.code);
        entity.setDescription(model.description);
        entity.setPricePerNight(model.pricePerNight);
        entity.setImage(model.image);
        entity.setRoomtypesByFkRoomtypeId(model.roomtypesByFkRoomtypeId);
        return entity;
    }

    public RoomModel createModel(RoomsEntity entity){
        RoomModel model = new RoomModel();
        model.id = entity.getId();
        model.code = entity.getCode();
        model.description = entity.getDescription();
        model.pricePerNight = entity.getPricePerNight();
        model.image = entity.getImage();
        model.roomtypesByFkRoomtypeId = entity.getRoomtypesByFkRoomtypeId();
        return model;
    }
}
