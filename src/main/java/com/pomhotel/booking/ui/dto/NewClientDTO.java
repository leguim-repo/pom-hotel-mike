package com.pomhotel.booking.ui.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

//--- DTO ----------------------------------------------------------
public class NewClientDTO {

    //--- Attributes -----------------------------------------------
    @NotNull
    @NotEmpty
    public String name;

    @NotNull
    @NotEmpty
    public String lastname;

    @NotNull
    @NotEmpty
    public String email;

    @NotNull
    @NotEmpty
    public String username;

    @NotNull
    @NotEmpty
    public String password;

    //--- Getters & Setters ----------------------------------------
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

}
