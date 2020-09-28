package com.pomhotel.booking.ui.dto;

import com.pomhotel.booking.application.models.BookingsModel;
import com.pomhotel.booking.application.models.RoomsModel;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class NewBookingDTO {
    @NotNull
    @NotEmpty
    public String username;

    @NotNull
    @NotEmpty
    public RoomsModel room;

    @NotNull
    @NotEmpty
    public Date checkIn;

    @NotNull
    @NotEmpty
    public Date checkOut;

    @NotNull
    @NotEmpty
    public int totalPrice;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public RoomsModel getRoom() {
        return room;
    }

    public void setRoom(RoomsModel room) {
        this.room = room;
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

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
