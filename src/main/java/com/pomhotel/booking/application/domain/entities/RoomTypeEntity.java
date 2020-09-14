package com.pomhotel.booking.application.domain.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "ROOMTYPES")
public class RoomTypeEntity implements Serializable {

    //Variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "ROOMTYPES", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RoomEntity> rooms;

    @OneToMany(mappedBy = "ROOMTYPES", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PreferencesEntity> preferences;

    //Constructor
    public RoomTypeEntity() {
    }

    public RoomTypeEntity(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    //Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<RoomEntity> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomEntity> rooms) {
        this.rooms = rooms;
    }

    public List<PreferencesEntity> getPreferences() {
        return preferences;
    }

    public void setPreferences(List<PreferencesEntity> preferences) {
        this.preferences = preferences;
    }
}