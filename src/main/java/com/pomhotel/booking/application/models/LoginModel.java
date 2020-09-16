package com.pomhotel.booking.application.models;

import com.pomhotel.booking.application.domain.entities.ClientsEntity;

public class LoginModel {
    public long id;
    public String username;
    public String password;
    public ClientsEntity clientsByFkClientId;

    //Constructor
    public LoginModel() {
    }
}
