package com.pomhotel.booking.application.models;

public class RoomsModel {

    //--- Attributes -----------------------------------------------
    public long id;
    public String code;
    public String description;
    public Double pricePerNight;
    public String image;
    public int guests;
    public RoomTypesModel roomtypesByFkRoomtypeId;

    //--- Constructor ----------------------------------------------
    public RoomsModel() {
    }

    //--- Getters & Setters ----------------------------------------
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPricePerNight() {
        return pricePerNight;
    }
    public void setPricePerNight(Double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public int getGuests() {
        return guests;
    }
    public void setGuests(int guests) {
        this.guests = guests;
    }

    public RoomTypesModel getRoomtypesByFkRoomtypeId() {
        return roomtypesByFkRoomtypeId;
    }
    public void setRoomtypesByFkRoomtypeId(RoomTypesModel roomtypesByFkRoomtypeId) {
        this.roomtypesByFkRoomtypeId = roomtypesByFkRoomtypeId;
    }

    @Override
    public String toString() {
        return "RoomsModel{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", pricePerNight=" + pricePerNight +
                ", image='" + image + '\'' +
                ", guests=" + guests +
                ", roomtypesByFkRoomtypeId=" + roomtypesByFkRoomtypeId +
                '}';
    }
}
