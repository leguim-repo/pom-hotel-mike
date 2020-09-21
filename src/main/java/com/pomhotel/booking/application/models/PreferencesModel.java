package com.pomhotel.booking.application.models;

import com.pomhotel.booking.application.domain.entities.RoomtypesEntity;

public class PreferencesModel {
    public long id;
    public Double priceLastSearch;
    public RoomtypesModel roomtypesByFkRoomtypeId;

    //Constructor
    public PreferencesModel() {
    }
}
