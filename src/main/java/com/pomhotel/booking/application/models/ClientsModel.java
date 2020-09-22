package com.pomhotel.booking.application.models;
import java.util.List;

public class ClientsModel {
    public long id;
    public String name;
    public String lastname;
    public String email;
    public List<BookingsModel> bookingsById;

    //Constructor
    public ClientsModel() {
    }
}
