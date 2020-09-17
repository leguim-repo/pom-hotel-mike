package com.pomhotel.booking.application.domain.entities;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "roomtypes", schema = "pom_hotel", catalog = "")
public class RoomtypesEntity {
    @Id
    @Column(name = "id", nullable = false)
    private long id;

    @Basic
    @Column(name = "name", nullable = true, length = 100)
    private String name;

    @Basic
    @Column(name = "description", nullable = true, length = 200)
    private String description;

    @OneToMany(mappedBy = "roomtypesByFkRoomtypeId")
    private Set<PreferencesEntity> preferencesById;

    @OneToMany(mappedBy = "roomtypesByFkRoomtypeId")
    private Set<RoomsEntity> roomsById;

    public long getId() {
        return id;
    }
    public void setId(long id) {
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

    public Set<PreferencesEntity> getPreferencesById() {
        return preferencesById;
    }
    public void setPreferencesById(Set<PreferencesEntity> preferencesById) {
        this.preferencesById = preferencesById;
    }

    public Set<RoomsEntity> getRoomsById() {
        return roomsById;
    }
    public void setRoomsById(Set<RoomsEntity> roomsById) {
        this.roomsById = roomsById;
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
}
