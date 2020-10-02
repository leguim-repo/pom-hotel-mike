package com.pomhotel.booking.application.services;

import com.pomhotel.booking.application.domain.entities.BookingsEntity;
import com.pomhotel.booking.application.domain.factories.BookingsFactory;
import com.pomhotel.booking.application.models.BookingsModel;
import com.pomhotel.booking.application.repositories.BookingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
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
