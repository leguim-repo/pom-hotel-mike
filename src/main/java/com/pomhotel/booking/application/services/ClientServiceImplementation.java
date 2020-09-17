package com.pomhotel.booking.application.services;

import com.pomhotel.booking.application.factories.ClientsFactory;
import com.pomhotel.booking.application.factories.RoomtypesFactory;
import com.pomhotel.booking.application.models.ClientsModel;
import com.pomhotel.booking.application.repositories.ClientRepository;
import com.pomhotel.booking.application.repositories.RoomtypesRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ClientServiceImplementation implements ClientService{
    ClientRepository repository;
    ClientsFactory factory;

    @Autowired
    public ClientServiceImplementation(ClientRepository repository, ClientsFactory factory) {
        this.repository = repository;
        this.factory = factory;
    }

    @Override
    public void Insert(ClientsModel employee) {

    }

    @Override
    public ClientsModel findById(long id) {
        return null;
    }

    @Override
    public List<ClientsModel> findAll() {
        return null;
    }

    @Override
    public void saveOrUpdate(ClientsModel model) {

    }

    @Override
    public void deleteById(long id) {

    }

    @Override
    public void delete(ClientsModel model) {

    }
}
