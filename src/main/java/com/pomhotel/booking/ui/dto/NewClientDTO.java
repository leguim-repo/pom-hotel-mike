package com.pomhotel.booking.ui.dto;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/* DTO para el formulario de registro de clientes */

public class NewClientDTO {
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
