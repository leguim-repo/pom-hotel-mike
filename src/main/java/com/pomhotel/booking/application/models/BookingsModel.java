package com.pomhotel.booking.application.models;

import com.pomhotel.booking.application.domain.entities.ClientsEntity;
import java.sql.Date;

public class BookingsModel {
    public long id;
    public Date checkIn;
    public Date checkOut;
    public Double totalPrice;
    //public ClientsModel clientsByFkClientId;

    //Constructor
    public BookingsModel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
