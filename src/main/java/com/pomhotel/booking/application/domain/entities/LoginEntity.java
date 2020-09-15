package com.pomhotel.booking.application.domain.entities;

import javax.persistence.*;

@Entity
@Table(name = "LOGINS")
public class LoginEntity {

    //Variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nickname;
    private String password;

    /*@OneToOne()
    @JoinColumn(name = "CLIENT_ID", unique = true, nullable = false)
    private ClientEntity client;*/

    //Constructors
    public LoginEntity() {
    }

    public LoginEntity(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }

    //Getters and Setters
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /*public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }*/
}
