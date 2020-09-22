package com.pomhotel.booking.application.models;
import java.util.List;

public class RoomtypesModel {
    public long id;
    public String name;
    public String description;
    public List<RoomsModel> roomsById;

    //Constructor
    public RoomtypesModel() {
    }
}
