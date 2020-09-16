package com.pomhotel.booking.application.models;

import com.pomhotel.booking.application.domain.entities.RoomtypesEntity;

public class RoomModel {
    public long id;
    public String code;
    public String description;
    public Double pricePerNight;
    public byte[] image;
    public RoomtypesEntity roomtypesByFkRoomtypeId;

    //Constructor
    public RoomModel() {
    }
}
