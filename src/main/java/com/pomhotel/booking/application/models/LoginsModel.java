package com.pomhotel.booking.application.models;

import com.pomhotel.booking.application.domain.entities.ClientsEntity;

public class LoginsModel {
    public long id;
    public String username;
    public String password;
    public ClientsModel clientsByFkClientId;

    //Constructor
    public LoginsModel() {
    }

    public LoginsModel(String username, String password, ClientsModel clientsByFkClientId) {
        this.username = username;
        this.password = password;
        this.clientsByFkClientId = clientsByFkClientId;
    }



    @Override
    public String toString() {
        return "LoginsModel{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", clientsByFkClientId=" + clientsByFkClientId +
                '}';
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
