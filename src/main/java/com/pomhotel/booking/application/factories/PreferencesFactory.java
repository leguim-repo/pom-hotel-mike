package com.pomhotel.booking.application.factories;

import com.pomhotel.booking.application.domain.entities.PreferencesEntity;
import com.pomhotel.booking.application.models.PreferencesModel;
import org.springframework.stereotype.Component;

@Component
public class PreferencesFactory {
    public PreferencesEntity createEntity(PreferencesModel model){
        PreferencesEntity entity = new PreferencesEntity();
        entity.setId(model.id);
        entity.setPriceLastSearch(model.priceLastSearch);
        entity.setRoomtypesByFkRoomtypeId(model.roomtypesByFkRoomtypeId);
        return entity;
    }

    public PreferencesModel createModel(PreferencesEntity entity){
        PreferencesModel model = new PreferencesModel();
        model.id = entity.getId();
        model.priceLastSearch = entity.getPriceLastSearch();
        model.roomtypesByFkRoomtypeId = entity.getRoomtypesByFkRoomtypeId();
        return model;
    }
}
