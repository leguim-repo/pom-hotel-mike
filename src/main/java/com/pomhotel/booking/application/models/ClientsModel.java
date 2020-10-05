package com.pomhotel.booking.application.models;

import java.util.List;

//--- Model --------------------------------------------------------
public class ClientsModel {

    //--- Attributes -----------------------------------------------
    public long id;
    public String name;
    public String lastname;
    public String email;
    public List<BookingsModel> bookingsById;

    //--- Constructor ----------------------------------------------
    public ClientsModel() {
    }

    public ClientsModel(String name, String lastname, String email) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
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

    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public List<BookingsModel> getBookingsById() {
        return bookingsById;
    }
    public void setBookingsById(List<BookingsModel> bookingsById) {
        this.bookingsById = bookingsById;
    }
}
