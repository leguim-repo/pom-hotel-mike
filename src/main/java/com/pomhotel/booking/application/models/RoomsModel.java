package com.pomhotel.booking.application.models;

import com.pomhotel.booking.application.domain.entities.RoomtypesEntity;

public class RoomsModel {
    public long id;
    public String code;
    public String description;
    public Double pricePerNight;
    public byte[] image;
    public RoomtypesModel roomtypesByFkRoomtypeId;

    //Constructor
    public RoomsModel() {
    }
}
