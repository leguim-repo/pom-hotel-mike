package com.pomhotel.booking.ui.dto;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/* DTO para el formulario de registro de clientes */

public class NewClientDTO {
    @NotNull
    @NotEmpty
    String name;

    @NotNull
    @NotEmpty
    String lastname;

    @NotNull
    @NotEmpty
    String email;

    @NotNull
    @NotEmpty
    String username;

    @NotNull
    @NotEmpty
    String password;

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

    @Override
    public String toString() {
        return "NewClientModel{" +
                "name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
