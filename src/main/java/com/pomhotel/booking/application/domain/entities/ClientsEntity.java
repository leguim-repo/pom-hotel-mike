package com.pomhotel.booking.application.domain.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Table(name = "clients", schema = "pom_hotel", catalog = "")
public class ClientsEntity {
    private long id;
    private String name;
    private String lastname;
    private String email;
    private Set<BookingsEntity> bookingsById;

    @OneToOne
    @PrimaryKeyJoinColumn
    private PreferencesEntity preferencesByFkPreferencesId;
    //private Collection<LoginsEntity> loginsById;

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
    @Column(name = "lastname", nullable = true, length = 100)
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 100)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientsEntity that = (ClientsEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(lastname, that.lastname) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastname, email);
    }

    @OneToMany(mappedBy = "clientsByFkClientId")
    public Set<BookingsEntity> getBookingsById() {
        return bookingsById;
    }

    public void setBookingsById(Set<BookingsEntity> bookingsById) {
        this.bookingsById = bookingsById;
    }

    public PreferencesEntity getPreferencesByFkPreferencesId() {
        return preferencesByFkPreferencesId;
    }

    public void setPreferencesByFkPreferencesId(PreferencesEntity preferencesByFkPreferencesId) {
        this.preferencesByFkPreferencesId = preferencesByFkPreferencesId;
    }

    /*@ManyToOne
    @JoinColumn(name = "fk_preferences_id", referencedColumnName = "id", table = "clients")
    public PreferencesEntity getPreferencesByFkPreferencesId() {
        return preferencesByFkPreferencesId;
    }

    public void setPreferencesByFkPreferencesId(PreferencesEntity preferencesByFkPreferencesId) {
        this.preferencesByFkPreferencesId = preferencesByFkPreferencesId;
    }

    @OneToMany(mappedBy = "clientsByFkClientId")
    public Collection<LoginsEntity> getLoginsById() {
        return loginsById;
    }


    public void setLoginsById(Collection<LoginsEntity> loginsById) {
        this.loginsById = loginsById;
    }*/
}
