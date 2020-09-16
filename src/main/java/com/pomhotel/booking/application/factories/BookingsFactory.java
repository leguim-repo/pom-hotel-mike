package com.pomhotel.booking.application.factories;

import com.pomhotel.booking.application.domain.entities.BookingsEntity;
import com.pomhotel.booking.application.models.BookingModel;
import org.springframework.stereotype.Component;

@Component
public class BookingsFactory {
    public BookingsEntity createEntity(BookingModel model){
        BookingsEntity entity = new BookingsEntity();
        entity.setId(model.id);
        entity.setCheckIn(model.checkIn);
        entity.setCheckOut(model.checkOut);
        entity.setTotalPrice(model.totalPrice);
        entity.setClientsByFkClientId(model.clientsByFkClientId);
        return entity;
    }

    public BookingModel createModel(BookingsEntity entity){
        BookingModel model = new BookingModel();
        model.id = entity.getId();
        model.checkIn = entity.getCheckIn();
        model.checkOut = entity.getCheckOut();
        model.totalPrice = entity.getTotalPrice();
        model.clientsByFkClientId = entity.getClientsByFkClientId();
        return model;
    }
}
