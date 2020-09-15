package com.pomhotel.booking.application.models;

import com.pomhotel.booking.application.domain.entities.BookingsEntity;
import java.util.Collection;

public class ClientModel {
    private long id;
    public String name;
    public String lastname;
    public String email;
    public Collection<BookingsEntity> bookingsById;

    //Constructor
    public ClientModel() {
    }
}
