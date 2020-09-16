package com.pomhotel.booking.application.models;

import com.pomhotel.booking.application.domain.entities.PreferencesEntity;
import com.pomhotel.booking.application.domain.entities.RoomsEntity;
import java.util.Collection;
import java.util.Set;

public class RoomTypeModel {
    public long id;
    public String name;
    public String description;
    public Set<PreferencesEntity> preferencesById;
    public Set<RoomsEntity> roomsById;

    //Constructor
    public RoomTypeModel() {
    }
}
