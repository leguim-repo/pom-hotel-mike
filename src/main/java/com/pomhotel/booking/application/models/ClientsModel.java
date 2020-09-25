package com.pomhotel.booking.application.models;
import java.util.List;

public class ClientsModel {
    public long id;
    public String name;
    public String lastname;
    public String email;
    public List<BookingsModel> bookingsById;

    //Constructors
    public ClientsModel() {
    }

    public ClientsModel(String name, String lastname, String email) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
    }
}
