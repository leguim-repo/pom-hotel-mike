package com.pomhotel.booking.application.factories;

import com.pomhotel.booking.application.domain.entities.PreferencesEntity;
import com.pomhotel.booking.application.domain.entities.RoomsEntity;
import com.pomhotel.booking.application.domain.entities.RoomtypesEntity;
import com.pomhotel.booking.application.models.PreferencesModel;
import com.pomhotel.booking.application.models.RoomsModel;
import com.pomhotel.booking.application.models.RoomtypesModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoomtypesFactory {
    private final RoomsFactory roomsFactory;
    private final PreferencesFactory preferencesFactory;

    @Autowired
    public RoomtypesFactory(RoomsFactory roomsFactory, PreferencesFactory preferencesFactory) {
        this.roomsFactory = roomsFactory;
        this.preferencesFactory = preferencesFactory;
    }

    public RoomtypesEntity createEntity(RoomtypesModel model){
        RoomtypesEntity entity = new RoomtypesEntity();
        entity.setId(model.id);
        entity.setName(model.name);
        entity.setDescription(model.description);

        List<PreferencesEntity> plist = model.preferencesById.stream()
                .map(m -> preferencesFactory.createEntity(m))
                .collect(Collectors.toList());
        entity.setPreferencesById(plist);

        List<RoomsEntity> rlist = model.roomsById.stream()
                .map(m -> roomsFactory.createEntity(m))
                .collect(Collectors.toList());
        entity.setRoomsById(rlist);

        return entity;
    }

    public RoomtypesModel createModel(RoomtypesEntity entity){
        RoomtypesModel model = new RoomtypesModel();
        model.id = entity.getId();
        model.name = entity.getName();
        model.description = entity.getDescription();

        model.preferencesById = entity.getPreferencesById().stream()
                .map(e -> preferencesFactory.createModel(e))
                .collect(Collectors.toList());

        model.roomsById = entity.getRoomsById().stream()
                .map(e -> roomsFactory.createModel(e))
                .collect(Collectors.toList());

        return model;
    }
}
