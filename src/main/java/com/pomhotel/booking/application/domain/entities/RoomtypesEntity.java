package com.pomhotel.booking.application.domain.entities;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Table(name = "roomtypes", schema = "pom_hotel", catalog = "")
public class RoomtypesEntity {
    private long id;
    private String name;
    private String description;
    private Set<PreferencesEntity> preferencesById;
    private Set<RoomsEntity> roomsById;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 200)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomtypesEntity that = (RoomtypesEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }

    @OneToMany(mappedBy = "roomtypesByFkRoomtypeId")
    public Set<PreferencesEntity> getPreferencesById() {
        return preferencesById;
    }

    public void setPreferencesById(Set<PreferencesEntity> preferencesById) {
        this.preferencesById = preferencesById;
    }

    @OneToMany(mappedBy = "roomtypesByFkRoomtypeId")
    public Set<RoomsEntity> getRoomsById() {
        return roomsById;
    }

    public void setRoomsById(Set<RoomsEntity> roomsById) {
        this.roomsById = roomsById;
    }
}
