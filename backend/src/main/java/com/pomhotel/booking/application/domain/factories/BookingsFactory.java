package com.pomhotel.booking.application.domain.factories;

import com.pomhotel.booking.application.domain.entities.BookingsEntity;
import com.pomhotel.booking.application.models.BookingDatesModel;
import com.pomhotel.booking.application.models.BookingsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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
        entity.setClientEmail(model.clientEmail);
        entity.setGuests(model.guests);
        entity.setBreakfast(model.breakfast);
        entity.setCarparking(model.carparking);
        entity.setSpa(model.spa);
        entity.setLaundry(model.laundry);
        entity.setShuttle(model.shuttle);
        entity.setCodediscount(model.codediscount);
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
        model.clientEmail = entity.getClientEmail();
        model.guests = entity.getGuests();
        model.breakfast = entity.isBreakfast();
        model.carparking = entity.isCarparking();
        model.spa = entity.isSpa();
        model.laundry = entity.isLaundry();
        model.shuttle = entity.isShuttle();
        model.codediscount = entity.getCodediscount();
        model.totalPrice = entity.getTotalPrice();
        model.clientsByFkClientId = clientsFactory.createModel(entity.getClientsByFkClientId());
        model.roomsByFKRoomId = roomsFactory.createModel(entity.getRoomsByFkRoomId());
        return model;
    }
    public List<BookingDatesModel> createModelDates(List<Object[]> entities){
        List<BookingDatesModel> listaDates = new ArrayList<>();

        for (Object[] b: entities) {
            Date checkin = (Date) b[0];
            Date checkout = (Date) b[1];
            System.out.println("checkin: "+checkin.toString());
            System.out.println("checkout: "+checkout.toString());
            BookingDatesModel model = new BookingDatesModel();
            model.setCheckIn(checkin);
            model.setCheckOut(checkout);
            listaDates.add(model);
        }



        return listaDates;
    }
}