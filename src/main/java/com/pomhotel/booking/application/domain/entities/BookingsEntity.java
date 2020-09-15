package com.pomhotel.booking.application.domain.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Table(name = "bookings", schema = "pom_hotel", catalog = "")
public class BookingsEntity {
    private long id;
    private Date checkIn;
    private Date checkOut;
    private Double totalPrice;
    private ClientsEntity clientsByFkClientId;

    @OneToOne
    @PrimaryKeyJoinColumn
    private RoomsEntity roomsByFkRoomId;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "checkIn", nullable = true)
    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    @Basic
    @Column(name = "checkOut", nullable = true)
    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    @Basic
    @Column(name = "totalPrice", nullable = true, precision = 0)
    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingsEntity that = (BookingsEntity) o;
        return id == that.id &&
                Objects.equals(checkIn, that.checkIn) &&
                Objects.equals(checkOut, that.checkOut) &&
                Objects.equals(totalPrice, that.totalPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, checkIn, checkOut, totalPrice);
    }

    @ManyToOne
    @JoinColumn(name = "fk_client_id", referencedColumnName = "id", table = "bookings")
    public ClientsEntity getClientsByFkClientId() {
        return clientsByFkClientId;
    }

    public void setClientsByFkClientId(ClientsEntity clientsByFkClientId) {
        this.clientsByFkClientId = clientsByFkClientId;
    }

    /*@ManyToOne
    @JoinColumn(name = "fk_room_id", referencedColumnName = "id", table = "bookings")
    public RoomsEntity getRoomsByFkRoomId() {
        return roomsByFkRoomId;
    }

    public void setRoomsByFkRoomId(RoomsEntity roomsByFkRoomId) {
        this.roomsByFkRoomId = roomsByFkRoomId;
    }*/

    public RoomsEntity getRoomsByFkRoomId() {
        return roomsByFkRoomId;
    }

    public void setRoomsByFkRoomId(RoomsEntity roomsByFkRoomId) {
        this.roomsByFkRoomId = roomsByFkRoomId;
    }
}
