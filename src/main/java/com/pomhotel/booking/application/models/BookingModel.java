package com.pomhotel.booking.application.models;

import com.pomhotel.booking.application.domain.entities.ClientsEntity;
import java.sql.Date;

public class BookingModel {
    public long id;
    public Date checkIn;
    public Date checkOut;
    public Double totalPrice;
    public ClientsEntity clientsByFkClientId;

    //Constructor
    public BookingModel() {
    }
}
