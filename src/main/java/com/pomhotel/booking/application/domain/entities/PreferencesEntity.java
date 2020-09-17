package com.pomhotel.booking.application.domain.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "preferences", schema = "pom_hotel", catalog = "")
public class PreferencesEntity {
    @Id
    @Column(name = "id", nullable = false)
    private long id;

    @Basic
    @Column(name = "priceLastSearch", nullable = true, precision = 0)
    private Double priceLastSearch;

    @ManyToOne
    @JoinColumn(name = "fk_roomtype_id", referencedColumnName = "id", table = "preferences")
    private RoomtypesEntity roomtypesByFkRoomtypeId;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public Double getPriceLastSearch() {
        return priceLastSearch;
    }
    public void setPriceLastSearch(Double priceLastSearch) {
        this.priceLastSearch = priceLastSearch;
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
        PreferencesEntity that = (PreferencesEntity) o;
        return id == that.id &&
                Objects.equals(priceLastSearch, that.priceLastSearch);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, priceLastSearch);
    }
}
