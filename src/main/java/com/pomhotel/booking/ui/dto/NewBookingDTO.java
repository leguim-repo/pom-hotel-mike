package com.pomhotel.booking.ui.dto;

import com.pomhotel.booking.application.models.RoomsModel;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class NewBookingDTO {
    @NotNull
    @NotEmpty
    public long roomId;

    @NotNull
    @NotEmpty
    public RoomsModel room;

    @NotNull
    @NotEmpty
    public String checkIn;

    @NotNull
    @NotEmpty
    public String checkOut;

    public int totalPrice;

    public RoomsModel getRoom() {
        return room;
    }

    public void setRoom(RoomsModel room) {
        this.room = room;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
