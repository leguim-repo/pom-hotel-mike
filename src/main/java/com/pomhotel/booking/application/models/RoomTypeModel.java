package com.pomhotel.booking.application.models;

import com.pomhotel.booking.application.domain.entities.PreferencesEntity;
import com.pomhotel.booking.application.domain.entities.RoomsEntity;
import java.util.Collection;

public class RoomTypeModel {
    private long id;
    public String name;
    public String description;
    public Collection<PreferencesEntity> preferencesById;
    public Collection<RoomsEntity> roomsById;

    //Constructor
    public RoomTypeModel() {
    }
}
