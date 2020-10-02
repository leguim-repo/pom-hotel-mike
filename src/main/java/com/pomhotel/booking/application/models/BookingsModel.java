package com.pomhotel.booking.application.models;

import java.sql.Date;

//--- Model --------------------------------------------------------
public class BookingsModel {

    //--- Attributes -----------------------------------------------
    public long id;
    public Date checkIn;
    public Date checkOut;
    public Double totalPrice;
    public ClientsModel clientsByFkClientId;
    public RoomsModel roomsByFKRoomId;

    //--- Constructor ----------------------------------------------
    public BookingsModel() {
    }

    //--- Getters & Setters ----------------------------------------
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

    public ClientsModel getClientsByFkClientId() {
        return clientsByFkClientId;
    }
    public void setClientsByFkClientId(ClientsModel clientsByFkClientId) {
        this.clientsByFkClientId = clientsByFkClientId;
    }

    public RoomsModel getRoomsByFKRoomId() {
        return roomsByFKRoomId;
    }
    public void setRoomsByFKRoomId(RoomsModel roomsByFKRoomId) {
        this.roomsByFKRoomId = roomsByFKRoomId;
    }
}
