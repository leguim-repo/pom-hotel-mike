package com.pomhotel.booking.application.domain.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "logins", schema = "pom_hotel", catalog = "")
public class LoginsEntity {

    @Id
    @Column(name = "id", nullable = false)
    private long id;

    @Basic
    @Column(name = "username", nullable = true, length = 100, unique = true)
    private String username;

    @Basic
    @Column(name = "password", nullable = true, length = 100)
    private String password;

    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private ClientsEntity clientsByFkClientId;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public ClientsEntity getClientsByFkClientId() {
        return clientsByFkClientId;
    }
    public void setClientsByFkClientId(ClientsEntity clientsByFkClientId) {
        this.clientsByFkClientId = clientsByFkClientId;
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
}
