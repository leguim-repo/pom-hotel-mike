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
}
