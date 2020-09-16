package com.pomhotel.booking.application.models;

import com.pomhotel.booking.application.domain.entities.RoomtypesEntity;

public class PreferenceModel {
    public long id;
    public Double priceLastSearch;
    public RoomtypesEntity roomtypesByFkRoomtypeId;

    //Constructor
    public PreferenceModel() {
    }
}
