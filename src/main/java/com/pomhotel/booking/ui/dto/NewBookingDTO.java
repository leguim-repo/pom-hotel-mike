package com.pomhotel.booking.ui.dto;

import com.pomhotel.booking.application.models.BookingsModel;
import com.pomhotel.booking.application.models.RoomsModel;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class NewBookingDTO {
    @NotNull
    @NotEmpty
    public String username;

    @NotNull
    @NotEmpty
    public RoomsModel room;

    @NotNull
    @NotEmpty
    public BookingsModel booking;

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

    public BookingsModel getBooking() {
        return booking;
    }

    public void setBooking(BookingsModel booking) {
        this.booking = booking;
    }
}
