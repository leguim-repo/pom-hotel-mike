package com.pomhotel.booking.application.domain.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

//--- Entity -------------------------------------------------------
@Entity
@Table(name = "clients", schema = "pom_hotel")
public class ClientsEntity implements Serializable {

    //--- Attributes -----------------------------------------------
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Basic
    @Column(name = "name", nullable = true, length = 100)
    private String name;

    @Basic
    @Column(name = "lastname", nullable = true, length = 100)
    private String lastname;

    @Basic
    @Column(name = "email", nullable = true, length = 100)
    private String email;

    @OneToMany(mappedBy = "clientsByFkClientId", fetch=FetchType.EAGER)
    private List<BookingsEntity> bookingsById;

    //--- Getters & Setters ---------------------------------------
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

    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }


    public List<BookingsEntity> getBookingsById() {
        return bookingsById;
    }
    public void setBookingsById(List<BookingsEntity> bookingsById) {
        this.bookingsById = bookingsById;
    }

    //--- Some general functions -----------------------------------
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
}
