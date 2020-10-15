package com.pomhotel.booking.application.domain.factories;

import com.pomhotel.booking.application.domain.entities.BookingsEntity;
import com.pomhotel.booking.application.models.BookingsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//--- Factory -------------------------------------------------------
@Component
public class BookingsFactory {

    //--- Needed Factories ------------------------------------------
    ClientsFactory clientsFactory;
    RoomsFactory roomsFactory;

    //--- Constructor -----------------------------------------------
    @Autowired
    public BookingsFactory(ClientsFactory clientsFactory, RoomsFactory roomsFactory) {
        this.clientsFactory = clientsFactory;
        this.roomsFactory = roomsFactory;
    }

    //--- Functions -------------------------------------------------
    public BookingsEntity createEntity(BookingsModel model){
        BookingsEntity entity = new BookingsEntity();
        entity.setId(model.id);
        entity.setCheckIn(model.checkIn);
        entity.setCheckOut(model.checkOut);
        entity.setTotalPrice(model.totalPrice);
        entity.setClientsByFkClientId(clientsFactory.createEntity(model.clientsByFkClientId));
        entity.setRoomsByFkRoomId(roomsFactory.createEntity(model.roomsByFKRoomId));
        return entity;
    }

    public BookingsModel createModel(BookingsEntity entity){
        BookingsModel model = new BookingsModel();
        model.id = entity.getId();
        model.checkIn = entity.getCheckIn();
        model.checkOut = entity.getCheckOut();
        model.totalPrice = entity.getTotalPrice();
        model.clientsByFkClientId = clientsFactory.createModel(entity.getClientsByFkClientId());
        model.roomsByFKRoomId = roomsFactory.createModel(entity.getRoomsByFkRoomId());
        return model;
    }
}