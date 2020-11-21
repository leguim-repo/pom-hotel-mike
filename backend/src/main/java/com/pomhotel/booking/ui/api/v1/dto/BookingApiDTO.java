package com.pomhotel.booking.ui.api.v1.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class BookingApiDTO {
    //--- Attributes -----------------------------------------------
    @NotNull
    @NotEmpty
    public long roomId;

    @NotNull
    @NotEmpty
    public String checkIn;

    @NotNull
    @NotEmpty
    public String checkOut;

    @NotNull
    @NotEmpty
    public String guests;

    public Boolean breakfastService;
    public Boolean carParkingService;
    public Boolean spaService;
    public Boolean laundryService;
    public Boolean shuttleService;
    public String codeDiscount;
    public String email;


    //--- Getters & Setters ----------------------------------------
    //Note: Spring hace su magia a traves de los Getters y los Setters. Se tienen que poner si o si.


    public BookingApiDTO() {
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
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

    public String getGuests() {
        return guests;
    }

    public void setGuests(String guests) {
        this.guests = guests;
    }

    public Boolean getBreakfastService() {
        return breakfastService;
    }

    public void setBreakfastService(Boolean breakfastService) {
        this.breakfastService = breakfastService;
    }

    public Boolean getCarParkingService() {
        return carParkingService;
    }

    public void setCarParkingService(Boolean carParkingService) {
        this.carParkingService = carParkingService;
    }

    public Boolean getSpaService() {
        return spaService;
    }

    public void setSpaService(Boolean spaService) {
        this.spaService = spaService;
    }

    public Boolean getLaundryService() {
        return laundryService;
    }

    public void setLaundryService(Boolean laundryService) {
        this.laundryService = laundryService;
    }

    public Boolean getShuttleService() {
        return shuttleService;
    }

    public void setShuttleService(Boolean shuttleService) {
        this.shuttleService = shuttleService;
    }

    public String getCodeDiscount() {
        return codeDiscount;
    }

    public void setCodeDiscount(String codeDiscount) {
        this.codeDiscount = codeDiscount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "BookingApiDTO{" +
                "roomId=" + roomId +
                ", checkIn='" + checkIn + '\'' +
                ", checkOut='" + checkOut + '\'' +
                ", guests='" + guests + '\'' +
                ", breakfastService=" + breakfastService +
                ", carParkingService=" + carParkingService +
                ", spaService=" + spaService +
                ", laundryService=" + laundryService +
                ", shuttleService=" + shuttleService +
                ", codeDiscount='" + codeDiscount + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
