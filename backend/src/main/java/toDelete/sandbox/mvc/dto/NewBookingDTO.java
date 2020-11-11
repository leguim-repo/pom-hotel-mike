package toDelete.sandbox.mvc.dto;

import com.pomhotel.booking.application.models.RoomsModel;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

//--- DTO ----------------------------------------------------------
public class NewBookingDTO {

    //--- Attributes -----------------------------------------------
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

    @NotNull
    @NotEmpty
    public String guests;

    public int totalPrice;


    //--- Getters & Setters ----------------------------------------
    //Note: Spring hace su magia a traves de los Getters y los Setters. Se tienen que poner si o si.
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

    public String getGuests() {
        return guests;
    }
    public void setGuests(String guests) {
        this.guests = guests;
    }

    @Override
    public String toString() {
        return "NewBookingDTO{" +
                "roomId=" + roomId +
                ", room=" + room +
                ", checkIn='" + checkIn + '\'' +
                ", checkOut='" + checkOut + '\'' +
                ", guests='" + guests + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
