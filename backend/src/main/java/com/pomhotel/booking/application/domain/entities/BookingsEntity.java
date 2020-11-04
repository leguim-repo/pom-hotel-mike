package com.pomhotel.booking.application.domain.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

//--- Entity -------------------------------------------------------
@Entity
@Table(name = "bookings", schema = "pom_hotel")
public class BookingsEntity implements Serializable {

    //--- Attributes -----------------------------------------------
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Basic
    @Column(name = "checkIn", nullable = true)
    private Date checkIn;

    @Basic
    @Column(name = "checkOut", nullable = true)
    private Date checkOut;

    @Basic
    @Column(name = "bookedDate", nullable = true)
    private Date bookedDate;

    @Basic
    @Column(name = "clientEmail", nullable = true, length = 100)
    private String clientEmail;

    @Basic
    @Column(name = "guests", nullable = true, precision = 0)
    private int guests;

    @Basic
    @Column(name = "breakfast")
    private boolean breakfast;

    @Basic
    @Column(name = "carparking")
    private boolean carparking;

    @Basic
    @Column(name = "spa")
    private boolean spa;

    @Basic
    @Column(name = "laundry")
    private boolean laundry;

    @Basic
    @Column(name = "shuttle")
    private boolean shuttle;

    @Basic
    @Column(name = "codediscount", nullable = true, length = 100)
    private String codediscount;

    @Basic
    @Column(name = "totalPrice", nullable = true, precision = 0)
    private Double totalPrice;

    @ManyToOne
    @JoinColumn(name = "fk_client_id", referencedColumnName = "id", table = "bookings")
    private ClientsEntity clientsByFkClientId;

    @OneToOne
    @JoinColumn(name = "fk_room_id", referencedColumnName = "id", table = "bookings")
    private RoomsEntity roomsByFkRoomId;

    //--- Getters & Setters ---------------------------------------


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

    public boolean isBreakfast() {
        return breakfast;
    }

    public void setBreakfast(boolean breakfast) {
        this.breakfast = breakfast;
    }

    public boolean isCarparking() {
        return carparking;
    }

    public void setCarparking(boolean carparking) {
        this.carparking = carparking;
    }

    public boolean isSpa() {
        return spa;
    }

    public void setSpa(boolean spa) {
        this.spa = spa;
    }

    public boolean isLaundry() {
        return laundry;
    }

    public void setLaundry(boolean laundry) {
        this.laundry = laundry;
    }

    public boolean isShuttle() {
        return shuttle;
    }

    public void setShuttle(boolean shuttle) {
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

    public ClientsEntity getClientsByFkClientId() {
        return clientsByFkClientId;
    }

    public void setClientsByFkClientId(ClientsEntity clientsByFkClientId) {
        this.clientsByFkClientId = clientsByFkClientId;
    }

    public RoomsEntity getRoomsByFkRoomId() {
        return roomsByFkRoomId;
    }

    public void setRoomsByFkRoomId(RoomsEntity roomsByFkRoomId) {
        this.roomsByFkRoomId = roomsByFkRoomId;
    }

    //--- Some general functions -----------------------------------

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingsEntity that = (BookingsEntity) o;
        return id == that.id &&
                guests == that.guests &&
                breakfast == that.breakfast &&
                carparking == that.carparking &&
                spa == that.spa &&
                laundry == that.laundry &&
                shuttle == that.shuttle &&
                checkIn.equals(that.checkIn) &&
                checkOut.equals(that.checkOut) &&
                bookedDate.equals(that.bookedDate) &&
                clientEmail.equals(that.clientEmail) &&
                codediscount.equals(that.codediscount) &&
                totalPrice.equals(that.totalPrice) &&
                clientsByFkClientId.equals(that.clientsByFkClientId) &&
                roomsByFkRoomId.equals(that.roomsByFkRoomId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, checkIn, checkOut, bookedDate, clientEmail, guests, breakfast, carparking, spa, laundry, shuttle, codediscount, totalPrice, clientsByFkClientId, roomsByFkRoomId);
    }
}
