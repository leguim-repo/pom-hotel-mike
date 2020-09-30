package com.pomhotel.booking.application.services;

import com.pomhotel.booking.application.domain.entities.RoomsEntity;
import com.pomhotel.booking.application.factories.RoomsFactory;
import com.pomhotel.booking.application.factories.RoomtypesFactory;
import com.pomhotel.booking.application.models.RoomsModel;
import com.pomhotel.booking.application.repositories.RoomsRepository;
import com.pomhotel.booking.application.repositories.RoomtypesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomsServiceImplementation implements RoomsService {

    RoomsRepository repository;
    RoomsFactory factory;

    @Autowired
    public RoomsServiceImplementation(RoomsRepository repository, RoomsFactory factory) {
        this.repository = repository;
        this.factory = factory;
    }

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
