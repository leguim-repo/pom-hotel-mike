package com.pomhotel.booking.application.services;

import com.pomhotel.booking.application.domain.entities.BookingsEntity;
import com.pomhotel.booking.application.domain.factories.BookingsFactory;
import com.pomhotel.booking.application.models.BookingDatesModel;
import com.pomhotel.booking.application.models.BookingsModel;
import com.pomhotel.booking.application.repositories.BookingsRepository;
import com.pomhotel.booking.ui.api.services.BusinessLogicApiService;
import com.pomhotel.booking.ui.api.services.BusinessLogicApiServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.naming.IdentityNamingStrategy;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

//--- Service ----------------------------------------------------------
@Service
public class BookingsServiceImplementation implements BookingsService{

    //--- Repositories & Factories needed ------------------------------
    BookingsRepository repository;
    BookingsFactory factory;

    //--- Constructor --------------------------------------------------
    @Autowired
    public BookingsServiceImplementation(BookingsRepository repository, BookingsFactory factory) {
        this.repository = repository;
        this.factory = factory;
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
            System.out.println("e: " + e.toString());
            System.out.println("days: " + daysToGenerate);
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

}
