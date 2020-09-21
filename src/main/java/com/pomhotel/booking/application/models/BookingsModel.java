package com.pomhotel.booking.application.models;

import com.pomhotel.booking.application.domain.entities.ClientsEntity;
import java.sql.Date;

public class BookingsModel {
    public long id;
    public Date checkIn;
    public Date checkOut;
    public Double totalPrice;
    public ClientsModel clientsByFkClientId;

    //Constructor
    public BookingsModel() {
    }
}
