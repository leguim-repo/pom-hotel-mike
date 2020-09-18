package com.pomhotel.booking.application.services;

import com.pomhotel.booking.application.domain.entities.ClientsEntity;
import com.pomhotel.booking.application.factories.ClientsFactory;
import com.pomhotel.booking.application.models.ClientsModel;
import com.pomhotel.booking.application.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class ClientServiceImplementation implements ClientService{
    ClientRepository repository;
    ClientsFactory factory;

    @Autowired
    public ClientServiceImplementation(ClientRepository repository, ClientsFactory factory) {
        this.repository = repository;
        this.factory = factory;
    }

    @Override
    public ClientsModel findById(long id) {
        return factory.createModel(repository.findById(id));
    }

    @Override
    public List<ClientsModel> findAll() {
        List<ClientsEntity> entities = repository.findAll();
        List<ClientsModel> models = entities.stream().map(entity -> {
            return factory.createModel(entity);
        }).collect(Collectors.toList());
        return models;
    }

    @Override
    public void saveOrUpdate(ClientsModel model) {
        repository.update(factory.createEntity(model));
    }

    @Override
    public void delete(ClientsModel model) {
        repository.delete(factory.createEntity(model));
    }
}
