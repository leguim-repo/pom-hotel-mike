package com.pomhotel.booking.application.models;

import java.util.List;

public class RoomtypesModel {

    //--- Attributes -----------------------------------------------
    public long id;
    public String name;
    public String description;
    public List<RoomsModel> roomsById;

    //--- Constructor ----------------------------------------------
    public RoomtypesModel() {
    }

    //--- Getters & Setters ----------------------------------------
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public List<RoomsModel> getRoomsById() {
        return roomsById;
    }
    public void setRoomsById(List<RoomsModel> roomsById) {
        this.roomsById = roomsById;
    }
}
