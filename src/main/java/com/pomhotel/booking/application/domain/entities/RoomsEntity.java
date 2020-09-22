package com.pomhotel.booking.application.domain.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "rooms", schema = "pom_hotel")
public class RoomsEntity implements Serializable {
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
    @Column(name = "image", nullable = true)
    private String image;


    @ManyToOne
    @JoinColumn(name = "fk_roomtype_id", referencedColumnName = "id", table = "rooms")
    private RoomtypesEntity roomtypesByFkRoomtypeId;

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

    public RoomtypesEntity getRoomtypesByFkRoomtypeId() {
        return roomtypesByFkRoomtypeId;
    }
    public void setRoomtypesByFkRoomtypeId(RoomtypesEntity roomtypesByFkRoomtypeId) {
        this.roomtypesByFkRoomtypeId = roomtypesByFkRoomtypeId;
    }

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
