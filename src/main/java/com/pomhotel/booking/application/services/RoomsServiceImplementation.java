package com.pomhotel.booking.application.services;

import com.pomhotel.booking.application.domain.entities.RoomsEntity;
import com.pomhotel.booking.application.domain.factories.RoomsFactory;
import com.pomhotel.booking.application.domain.factories.RoomtypesFactory;
import com.pomhotel.booking.application.models.RoomsModel;
import com.pomhotel.booking.application.repositories.RoomsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//--- Service ----------------------------------------------------------
@Service
public class RoomsServiceImplementation implements RoomsService {

    //--- Repositories & Factories needed ------------------------------
    RoomsRepository repository;
    RoomsFactory factory;

    //--- Constructor --------------------------------------------------
    @Autowired
    public RoomsServiceImplementation(RoomsRepository repository, RoomsFactory factory) {
        this.repository = repository;
        this.factory = factory;
    }

    //--- Functions ----------------------------------------------------
    @Override
    public RoomsModel findById(long id) {
        return factory.createModel(repository.findById(id));
    }

    @Override
    public List<RoomsModel> findAll() {
        List<RoomsEntity> entities = repository.findAll();
        List<RoomsModel> models = entities.stream().map(entity -> {
            return factory.createModel(entity);
        }).collect(Collectors.toList());
        return models;
    }

    @Override
    public List<RoomsModel> findApplyingFilter(int guests, int minPrice, int maxPrice, long idType){
        List<RoomsEntity> entities = repository.findApplyingFilter(guests, minPrice, maxPrice, idType);
        List<RoomsModel> models = entities.stream().map(entity -> {
            return factory.createModel(entity);
        }).collect(Collectors.toList());
        return models;
    }

    @Override
    public void saveOrUpdate(RoomsModel model) {
        repository.saveOrUpdate(factory.createEntity(model));
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    @Override
    public void delete(RoomsModel model) {
        repository.delete(factory.createEntity(model));
    }

}
