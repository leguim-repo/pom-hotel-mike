package com.pomhotel.booking.application.services;

import com.pomhotel.booking.application.domain.entities.ClientsEntity;
import com.pomhotel.booking.application.domain.entities.LoginsEntity;
import com.pomhotel.booking.application.domain.entities.RoomtypesEntity;
import com.pomhotel.booking.application.factories.ClientsFactory;
import com.pomhotel.booking.application.factories.LoginsFactory;
import com.pomhotel.booking.application.models.ClientsModel;
import com.pomhotel.booking.application.models.LoginsModel;
import com.pomhotel.booking.application.models.RoomtypesModel;
import com.pomhotel.booking.application.repositories.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoginServiceImplementation implements LoginService{

    LoginRepository repository;
    LoginsFactory loginsFactory;
    ClientsFactory clientsFactory;

    @Autowired
    public LoginServiceImplementation(LoginRepository repository, LoginsFactory loginsFactory, ClientsFactory clientsFactory) {
        this.repository = repository;
        this.loginsFactory = loginsFactory;
        this.clientsFactory = clientsFactory;
    }

    @Override
    public ClientsModel authentification (LoginsModel model) {
        ClientsModel clientsModel;

        LoginsEntity loginsEntity = loginsFactory.createEntity(model);
        ClientsEntity clientsEntity = repository.authentification(loginsEntity);

        if (clientsEntity != null){
            clientsModel = clientsFactory.createModel(clientsEntity);
        }
        else{
            clientsModel = null;
        }

        return clientsModel;
    }

    @Override
    public List<LoginsModel> findAll() {
        List<LoginsEntity> entities = repository.findAll();
        List<LoginsModel> models = entities.stream().map(entity -> {
            return loginsFactory.createModel(entity);
        }).collect(Collectors.toList());
        return models;
    }
}
