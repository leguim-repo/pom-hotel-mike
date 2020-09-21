package com.pomhotel.booking.application.factories;

import com.pomhotel.booking.application.domain.entities.BookingsEntity;
import com.pomhotel.booking.application.domain.entities.ClientsEntity;
import com.pomhotel.booking.application.models.ClientsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClientsFactory {
    private final PreferencesFactory preferencesFactory;
    private final BookingsFactory bookingsFactory;

    @Autowired
    public ClientsFactory(PreferencesFactory preferencesFactory, BookingsFactory bookingsFactory) {
        this.preferencesFactory = preferencesFactory;
        this.bookingsFactory = bookingsFactory;
    }

    public ClientsEntity createEntity(ClientsModel model){
        ClientsEntity entity = new ClientsEntity();
        entity.setId(model.id);
        entity.setName(model.name);
        entity.setLastname(model.lastname);
        entity.setEmail(model.email);
        entity.setPreferencesByFkPreferencesId(preferencesFactory.createEntity(model.preferencesByFkPreferencesId));
        //entity.setBookingsById(bookingsFactory.createEntity(model.bookingsById));
        List<BookingsEntity> list = model.bookingsById.stream()
                .map(m -> bookingsFactory.createEntity(m))
                .collect(Collectors.toList());
        entity.setBookingsById(list);
        return entity;
    }

    public ClientsModel createModel(ClientsEntity entity){
        ClientsModel model = new ClientsModel();
        model.id = entity.getId();
        model.name = entity.getName();
        model.lastname = entity.getLastname();
        model.email = entity.getEmail();
        model.preferencesByFkPreferencesId = preferencesFactory.createModel(entity.getPreferencesByFkPreferencesId());
        //model.bookingsById = preferencesFactory.createModel(entity.getBookingsById());
        model.bookingsById = entity.getBookingsById().stream()
                .map(e -> bookingsFactory.createModel(e))
                .collect(Collectors.toList());
        return model;
    }
}