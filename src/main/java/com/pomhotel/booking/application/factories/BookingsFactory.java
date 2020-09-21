package com.pomhotel.booking.application.factories;

import com.pomhotel.booking.application.domain.entities.BookingsEntity;
import com.pomhotel.booking.application.models.BookingsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookingsFactory {
    private final ClientsFactory clientsFactory;

    @Autowired
    public BookingsFactory(ClientsFactory clientsFactory) {
        this.clientsFactory = clientsFactory;
    }

    public BookingsEntity createEntity(BookingsModel model){
        BookingsEntity entity = new BookingsEntity();
        entity.setId(model.id);
        entity.setCheckIn(model.checkIn);
        entity.setCheckOut(model.checkOut);
        entity.setTotalPrice(model.totalPrice);
        entity.setClientsByFkClientId(clientsFactory.createEntity(model.clientsByFkClientId));
        return entity;
    }

    public BookingsModel createModel(BookingsEntity entity){
        BookingsModel model = new BookingsModel();
        model.id = entity.getId();
        model.checkIn = entity.getCheckIn();
        model.checkOut = entity.getCheckOut();
        model.totalPrice = entity.getTotalPrice();
        model.clientsByFkClientId = clientsFactory.createModel(entity.getClientsByFkClientId());
        return model;
    }
}