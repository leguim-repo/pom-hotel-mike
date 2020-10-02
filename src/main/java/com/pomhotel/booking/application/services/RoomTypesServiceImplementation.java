package com.pomhotel.booking.application.services;

import com.pomhotel.booking.application.domain.entities.RoomtypesEntity;
import com.pomhotel.booking.application.domain.factories.RoomtypesFactory;
import com.pomhotel.booking.application.models.RoomtypesModel;
import com.pomhotel.booking.application.repositories.RoomtypesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//--- Service ----------------------------------------------------------
@Service
public class RoomTypesServiceImplementation implements RoomTypesService {

    //--- Repositories & Factories needed ------------------------------
    RoomtypesRepository repository;
    RoomtypesFactory factory;

    //--- Constructor --------------------------------------------------
    @Autowired
    public RoomTypesServiceImplementation(RoomtypesRepository repository, RoomtypesFactory factory) {
        this.repository = repository;
        this.factory = factory;
    }

    //--- Functions ----------------------------------------------------
    @Override
    public RoomtypesModel findById(long id) {
        return factory.createModel(repository.findById(id));
    }

    @Override
    public List<RoomtypesModel> findAll() {
        List<RoomtypesEntity> entities = repository.findAll();
        List<RoomtypesModel> models = entities.stream().map(entity -> {
            return factory.createModel(entity);
        }).collect(Collectors.toList());
        return models;
    }

    @Override
    public void saveOrUpdate(RoomtypesModel model) {
        repository.saveOrUpdate(factory.createEntity(model));
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    @Override
    public void delete(RoomtypesModel model) {
        repository.delete(factory.createEntity(model));
    }

}
