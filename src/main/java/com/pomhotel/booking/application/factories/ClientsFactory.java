package com.pomhotel.booking.application.factories;

import com.pomhotel.booking.application.domain.entities.ClientsEntity;
import com.pomhotel.booking.application.models.ClientModel;
import org.springframework.stereotype.Component;

@Component
public class ClientsFactory {
    public ClientsEntity createEntity(ClientModel model){
        ClientsEntity entity = new ClientsEntity();
        entity.setId(model.id);
        entity.setName(model.name);
        entity.setLastname(model.lastname);
        entity.setEmail(model.email);
        entity.setPreferencesByFkPreferencesId(model.preferencesByFkPreferencesId);
        entity.setBookingsById(model.bookingsById);
        return entity;
    }

    public ClientModel createModel(ClientsEntity entity){
        ClientModel model = new ClientModel();
        model.id = entity.getId();
        model.name = entity.getName();
        model.lastname = entity.getLastname();
        model.email = entity.getEmail();
        model.preferencesByFkPreferencesId= entity.getPreferencesByFkPreferencesId();
        model.bookingsById = entity.getBookingsById();
        return model;
    }
}
