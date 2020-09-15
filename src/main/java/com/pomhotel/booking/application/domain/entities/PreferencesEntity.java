package com.pomhotel.booking.application.domain.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Table(name = "preferences", schema = "pom_hotel", catalog = "")
public class PreferencesEntity {
    private long id;
    private Double priceLastSearch;
    private Collection<ClientsEntity> clientsById;
    private RoomtypesEntity roomtypesByFkRoomtypeId;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "priceLastSearch", nullable = true, precision = 0)
    public Double getPriceLastSearch() {
        return priceLastSearch;
    }

    public void setPriceLastSearch(Double priceLastSearch) {
        this.priceLastSearch = priceLastSearch;
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

    @OneToMany(mappedBy = "preferencesByFkPreferencesId")
    public Collection<ClientsEntity> getClientsById() {
        return clientsById;
    }

    public void setClientsById(Collection<ClientsEntity> clientsById) {
        this.clientsById = clientsById;
    }

    @ManyToOne
    @JoinColumn(name = "fk_roomtype_id", referencedColumnName = "id", table = "preferences")
    public RoomtypesEntity getRoomtypesByFkRoomtypeId() {
        return roomtypesByFkRoomtypeId;
    }

    public void setRoomtypesByFkRoomtypeId(RoomtypesEntity roomtypesByFkRoomtypeId) {
        this.roomtypesByFkRoomtypeId = roomtypesByFkRoomtypeId;
    }
}
