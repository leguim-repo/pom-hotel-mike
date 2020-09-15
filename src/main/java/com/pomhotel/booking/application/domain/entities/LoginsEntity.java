package com.pomhotel.booking.application.domain.entities;

import javax.persistence.*;
import java.util.Objects;

@Table(name = "logins", schema = "pom_hotel", catalog = "")
public class LoginsEntity {
    private long id;
    private String username;
    private String password;

    @OneToOne
    @PrimaryKeyJoinColumn
    private ClientsEntity clientsByFkClientId;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username", nullable = true, length = 100)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password", nullable = true, length = 100)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginsEntity that = (LoginsEntity) o;
        return id == that.id &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password);
    }

    public ClientsEntity getClientsByFkClientId() {
        return clientsByFkClientId;
    }

    public void setClientsByFkClientId(ClientsEntity clientsByFkClientId) {
        this.clientsByFkClientId = clientsByFkClientId;
    }

    /*@ManyToOne
    @JoinColumn(name = "fk_client_id", referencedColumnName = "id", table = "logins")
    public ClientsEntity getClientsByFkClientId() {
        return clientsByFkClientId;
    }

    public void setClientsByFkClientId(ClientsEntity clientsByFkClientId) {
        this.clientsByFkClientId = clientsByFkClientId;
    }*/
}
