package com.pomhotel.booking.application.models;

import java.sql.Date;

//--- Model --------------------------------------------------------
public class BookingsModel {

    //--- Attributes -----------------------------------------------
    public long id;
    public Date checkIn;
    public Date checkOut;
    public Date bookedDate;
    public String clientEmail;
    public int guests;
    public Boolean breakfast;
    public Boolean carparking;
    public Boolean spa;
    public Boolean laundry;
    public Boolean shuttle;
    public String codediscount;
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

    public Date getBookedDate() {
        return bookedDate;
    }

    public void setBookedDate(Date bookedDate) {
        this.bookedDate = bookedDate;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public int getGuests() {
        return guests;
    }

    public void setGuests(int guests) {
        this.guests = guests;
    }

    public Boolean getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(Boolean breakfast) {
        this.breakfast = breakfast;
    }

    public Boolean getCarparking() {
        return carparking;
    }

    public void setCarparking(Boolean carparking) {
        this.carparking = carparking;
    }

    public Boolean getSpa() {
        return spa;
    }

    public void setSpa(Boolean spa) {
        this.spa = spa;
    }

    public Boolean getLaundry() {
        return laundry;
    }

    public void setLaundry(Boolean laundry) {
        this.laundry = laundry;
    }

    public Boolean getShuttle() {
        return shuttle;
    }

    public void setShuttle(Boolean shuttle) {
        this.shuttle = shuttle;
    }

    public String getCodediscount() {
        return codediscount;
    }

    public void setCodediscount(String codediscount) {
        this.codediscount = codediscount;
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
