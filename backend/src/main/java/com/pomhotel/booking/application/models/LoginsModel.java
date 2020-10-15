package com.pomhotel.booking.application.models;

public class LoginsModel {

    //--- Attributes -----------------------------------------------
    public long id;
    public String username;
    public String password;
    public ClientsModel clientsByFkClientId;

    //--- Constructor ----------------------------------------------
    public LoginsModel() {
    }

    public LoginsModel(String username, String password, ClientsModel clientsByFkClientId) {
        this.username = username;
        this.password = password;
        this.clientsByFkClientId = clientsByFkClientId;
    }

    //--- Getters & Setters ----------------------------------------
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public ClientsModel getClientsByFkClientId() {
        return clientsByFkClientId;
    }
    public void setClientsByFkClientId(ClientsModel clientsByFkClientId) {
        this.clientsByFkClientId = clientsByFkClientId;
    }
}
