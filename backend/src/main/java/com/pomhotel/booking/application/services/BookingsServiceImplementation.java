package com.pomhotel.booking.application.services;

import com.pomhotel.booking.application.domain.entities.BookingsEntity;
import com.pomhotel.booking.application.domain.factories.BookingsFactory;
import com.pomhotel.booking.application.models.BookingDatesModel;
import com.pomhotel.booking.application.models.BookingsModel;
import com.pomhotel.booking.application.models.RoomsModel;
import com.pomhotel.booking.application.repositories.BookingsRepository;
import com.pomhotel.booking.ui.api.dto.BookingApiDTO;
import com.pomhotel.booking.ui.api.dto.CalculatedBookDTO;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

//--- Service ----------------------------------------------------------
@Service
public class BookingsServiceImplementation implements BookingsService{
    private static final org.apache.commons.logging.Log Logger = LogFactory.getLog("BookingsServiceImplementation.class");

    //--- Repositories & Factories needed ------------------------------
    BookingsRepository repository;
    BookingsFactory factory;
    RoomsService roomsService;
    ClientLoginService clientsService;
    BusinessLogicApiService businessLogicService;

    //--- Constructor --------------------------------------------------
    @Autowired
    public BookingsServiceImplementation(BookingsRepository repository, BookingsFactory factory,RoomsService roomsService,ClientLoginService clientsService  , BusinessLogicApiService businessLogicService) {
        this.repository = repository;
        this.factory = factory;
        this.roomsService = roomsService;
        this.clientsService = clientsService;
        this.businessLogicService = businessLogicService;
    }

    //--- Functions ----------------------------------------------------
    @Override
    public BookingsModel findById(long id) {
        return factory.createModel(repository.findById(id));
    }

    @Override
    public List<BookingsModel> findAll() {
        List<BookingsEntity> entities = repository.findAll();
        List<BookingsModel> models = entities.stream().map(entity -> {
            return factory.createModel(entity);
        }).collect(Collectors.toList());
        return models;
    }

    @Override
    public List<BookingDatesModel> getBookedDatesByRoomId(long id) {
        List<Object[]> entities = repository.getBookedDatesByRoomId(id);
        List<BookingDatesModel> models = factory.createModelDates(entities);
        return models;
    }

    @Override
    public List<Date> generateBookedDatesInRunTime(List<BookingDatesModel> model) {
        BusinessLogicApiService businessLogicService = new BusinessLogicApiServiceImplementation();
        List<Date> bookedDates = new ArrayList<>();
        for (BookingDatesModel e: model) {
            long daysToGenerate = businessLogicService.getDaysBetweenTwoDates(e.checkIn, e.checkOut);
            Logger.info("e: " + e.toString());
            Logger.info("days: " + daysToGenerate);
            bookedDates.add(e.checkIn);
            for (int d = 0; d < daysToGenerate; d++) {
                Date last = bookedDates.get(bookedDates.size() - 1);
                Date newDay = new Date(last.getTime() + TimeUnit.DAYS.toMillis(1));
                bookedDates.add(newDay);
            }
        }
        return bookedDates;
    }

    @Override
    public Integer save(BookingsModel model) {
        Integer id = 0;
        id = repository.save(factory.createEntity(model));
        return id;
    }

    @Override
    public void saveOrUpdate(BookingsModel model) {
        repository.saveOrUpdate(factory.createEntity(model));
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    @Override
    public void delete(BookingsModel model) {
        repository.delete(factory.createEntity(model));
    }

    @Override
    public Integer SaveNewBooking(BookingApiDTO dto) {
        CalculatedBookDTO bookingCalculado;
        RoomsModel room;
        Integer bookingId = 0;
        BookingsModel model = new BookingsModel();

        try {
            room = roomsService.findById(dto.roomId);
            model.checkIn = Date.valueOf(dto.checkIn);
            model.checkOut = Date.valueOf(dto.checkOut);
            model.guests = room.guests;
            model.clientsByFkClientId = clientsService.findClientByUsername("miguel"); // asignado a un cliente por defecto al no tener login
            model.breakfast = dto.breakfastService;
            model.carparking = dto.carParkingService;
            model.spa = dto.spaService;
            model.laundry = dto.laundryService;
            model.shuttle = dto.shuttleService;
            model.codediscount = dto.codeDiscount;
            model.clientEmail = dto.email;
            model.roomsByFKRoomId = room;
            bookingCalculado = businessLogicService.callToCalculateBooking(dto);
            model.totalPrice = bookingCalculado.totalBookingPrice;
            Logger.info("bookroomnow.model: "+model.toString());
            bookingId=this.save(model);

        }
        catch (Exception e) {
            bookingId=0;

        }

        return bookingId;
    }
}
