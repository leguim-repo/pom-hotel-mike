package com.pomhotel.booking.application.domain.entities;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "CLIENTS")
public class ClientEntity implements Serializable {

    //Variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String first_name;

    private String last_name;

    private String email;

    @OneToOne(mappedBy = "CLIENTS", cascade = CascadeType.ALL, orphanRemoval = true)
    private LoginEntity login;

    @OneToMany(mappedBy = "CLIENTS", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookingEntity> bookings;

    //Constructors
    public ClientEntity() {
    }

    public ClientEntity(Long id, String fname, String lname, String email) {
        this.id = id;
        this.first_name = fname;
        this.last_name = lname;
        this.email = email;
    }

    //Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LoginEntity getLogin() {
        return login;
    }

    public void setLogin(LoginEntity login) {
        this.login = login;
    }

    public List<BookingEntity> getBookings() {
        return bookings;
    }

    public void setBookings(List<BookingEntity> bookings) {
        this.bookings = bookings;
    }
}
