package com.pomhotel.booking.application.domain.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "BOOKINGS")
public class RoomEntity implements Serializable {

    //Variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String description;
    private double pricePerNight;

    @ManyToOne()
    @JoinColumn(name = "ROOMTYPE_ID")
    private RoomTypeEntity roomType;

    //Constructors
    public RoomEntity() {
    }

    public RoomEntity(Long id, String code, String description, double pricePerNight, RoomTypeEntity roomType) {
        this.id = id;
        this.code = code;
        this.description = description;
        this.pricePerNight = pricePerNight;
        this.roomType = roomType;
    }

    //Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public RoomTypeEntity getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomTypeEntity roomType) {
        this.roomType = roomType;
    }
}