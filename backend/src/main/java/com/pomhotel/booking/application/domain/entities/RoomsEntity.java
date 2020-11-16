package com.pomhotel.booking.application.domain.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

//--- Entity -------------------------------------------------------
@Entity
@Table(name = "rooms", schema = "pom_hotel")
public class RoomsEntity implements Serializable {

    //--- Attributes -----------------------------------------------
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Basic
    @Column(name = "code", nullable = true, length = 100)
    private String code;

    @Basic
    @Column(name = "description", nullable = true, length = 200)
    private String description;

    @Basic
    @Column(name = "pricePerNight", nullable = true, precision = 0)
    private Double pricePerNight;

    @Basic
    @Column(name = "guests", nullable = true, precision = 0)
    private int guests;

    @Basic
    @Column(name = "image", nullable = true)
    private String image;

    @ManyToOne
    @JoinColumn(name = "fk_roomtype_id", referencedColumnName = "id", table = "rooms")
    private RoomtypesEntity roomtypesByFkRoomtypeId;

    //--- Getters & Setters ---------------------------------------
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPricePerNight() {
        return pricePerNight;
    }
    public void setPricePerNight(Double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public int getGuests() {
        return guests;
    }
    public void setGuests(int guests) {
        this.guests = guests;
    }

    public RoomtypesEntity getRoomtypesByFkRoomtypeId() {
        return roomtypesByFkRoomtypeId;
    }
    public void setRoomtypesByFkRoomtypeId(RoomtypesEntity roomtypesByFkRoomtypeId) {
        this.roomtypesByFkRoomtypeId = roomtypesByFkRoomtypeId;
    }

    //--- Some general functions -----------------------------------
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomsEntity that = (RoomsEntity) o;
        return id == that.id &&
                Objects.equals(code, that.code) &&
                Objects.equals(description, that.description) &&
                Objects.equals(pricePerNight, that.pricePerNight) &&
                Objects.equals(image, that.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, description, pricePerNight, image);
    }
}
