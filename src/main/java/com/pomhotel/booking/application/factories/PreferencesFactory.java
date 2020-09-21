package com.pomhotel.booking.application.factories;

import com.pomhotel.booking.application.domain.entities.PreferencesEntity;
import com.pomhotel.booking.application.models.PreferencesModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PreferencesFactory {
    private final RoomtypesFactory roomtypesFactory;

    @Autowired
    public PreferencesFactory(RoomtypesFactory roomtypesFactory) {
        this.roomtypesFactory = roomtypesFactory;
    }

    public PreferencesEntity createEntity(PreferencesModel model){
        PreferencesEntity entity = new PreferencesEntity();
        entity.setId(model.id);
        entity.setPriceLastSearch(model.priceLastSearch);
        entity.setRoomtypesByFkRoomtypeId(roomtypesFactory.createEntity(model.roomtypesByFkRoomtypeId));
        return entity;
    }

    public PreferencesModel createModel(PreferencesEntity entity){
        PreferencesModel model = new PreferencesModel();
        model.id = entity.getId();
        model.priceLastSearch = entity.getPriceLastSearch();
        model.roomtypesByFkRoomtypeId = roomtypesFactory.createModel(entity.getRoomtypesByFkRoomtypeId());
        return model;
    }
}
