package com.pomhotel.booking.application.models;

import com.pomhotel.booking.application.domain.entities.RoomsEntity;

import javax.persistence.Entity;
import java.sql.Date;

public class BookingDatesModel {
    public Date checkIn;
    public Date checkOut;

    public BookingDatesModel() {
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

    @Override
    public String toString() {
        return "BookingDatesModel{" +
                "checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                '}';
    }

}
