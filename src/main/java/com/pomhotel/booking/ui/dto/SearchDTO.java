package com.pomhotel.booking.ui.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

//--- DTO ----------------------------------------------------------
public class SearchDTO {

    //--- Attributes -----------------------------------------------
    @NotNull
    @NotEmpty
    public String checkin;

    @NotNull
    @NotEmpty
    public String checkout;

    @NotNull
    @NotEmpty
    public String guests;

    @NotNull
    @NotEmpty
    public String minprice;

    @NotNull
    @NotEmpty
    public String maxprice;

    @NotNull
    @NotEmpty
    public String type;

    //--- Getters & Setters ----------------------------------------
    public String getCheckin() {
        return checkin;
    }
    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }
    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public String getGuests() {
        return guests;
    }
    public void setGuests(String guests) {
        this.guests = guests;
    }

    public String getMinprice() {
        return minprice;
    }
    public void setMinprice(String minprice) {
        this.minprice = minprice;
    }

    public String getMaxprice() {
        return maxprice;
    }
    public void setMaxprice(String maxprice) {
        this.maxprice = maxprice;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
}
