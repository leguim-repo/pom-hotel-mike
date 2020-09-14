package com.pomhotel.booking.application.domain.entities;

import javax.persistence.*;

public class PreferencesEntity {
    //Variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double priceLastSearch;

    @ManyToOne()
    @JoinColumn(name = "ROOMTYPE_ID")
    private RoomTypeEntity roomType;

    //Constructors
    public PreferencesEntity() {
    }

    public PreferencesEntity(Long id, double priceLastSearch, RoomTypeEntity roomType) {
        this.id = id;
        this.priceLastSearch = priceLastSearch;
        this.roomType = roomType;
    }

    //Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPriceLastSearch() {
        return priceLastSearch;
    }

    public void setPriceLastSearch(double priceLastSearch) {
        this.priceLastSearch = priceLastSearch;
    }

    public RoomTypeEntity getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomTypeEntity roomType) {
        this.roomType = roomType;
    }
}
