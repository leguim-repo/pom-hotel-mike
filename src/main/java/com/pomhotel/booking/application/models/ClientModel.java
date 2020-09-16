package com.pomhotel.booking.application.models;

import com.pomhotel.booking.application.domain.entities.BookingsEntity;
import com.pomhotel.booking.application.domain.entities.PreferencesEntity;

import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.Collection;
import java.util.Set;

public class ClientModel {
    public long id;
    public String name;
    public String lastname;
    public String email;
    public Set<BookingsEntity> bookingsById;
    public PreferencesEntity preferencesByFkPreferencesId;

    //Constructor
    public ClientModel() {
    }
}
