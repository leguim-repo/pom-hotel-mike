package com.pomhotel.booking.application.services;

import com.pomhotel.booking.application.factories.BookingsFactory;
import com.pomhotel.booking.application.models.BookingsModel;
import com.pomhotel.booking.application.models.RoomsModel;
import com.pomhotel.booking.application.repositories.BookingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingsServiceImplementation implements BookingsService{
    BookingsRepository repository;
    BookingsFactory factory;

    @Autowired
    public BookingsServiceImplementation(BookingsRepository repository, BookingsFactory factory) {
        this.repository = repository;
        this.factory = factory;
    }

    @Override
    public BookingsModel findById(long id) {
        return null;
    }

    @Override
    public List<BookingsModel> findAll() {
        return null;
    }

    @Override
    public void saveOrUpdate(BookingsModel model) {
        //Verificar/Comprobar que todos los datos son correctos y los calculos de fechas tb.
        repository.saveOrUpdate(factory.createEntity(model));
    }

    @Override
    public void deleteById(long id) {

    }

    @Override
    public void delete(RoomsModel model) {

    }

}
