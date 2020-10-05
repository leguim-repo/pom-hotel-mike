package com.pomhotel.booking.application.services;

import com.pomhotel.booking.application.domain.factories.ClientsFactory;
import com.pomhotel.booking.application.domain.factories.LoginsFactory;
import com.pomhotel.booking.application.models.ClientsModel;
import com.pomhotel.booking.application.models.LoginsModel;
import com.pomhotel.booking.application.repositories.ClientLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//--- Service ----------------------------------------------------------
@Service
public class ClientLoginServiceImplementation implements ClientLoginService {

    //--- Repositories & Factories needed ------------------------------
    ClientLoginRepository repository;
    LoginsFactory loginsFactory;
    ClientsFactory clientsFactory;

    //--- Constructor --------------------------------------------------
    @Autowired
    public ClientLoginServiceImplementation(ClientLoginRepository repository, LoginsFactory loginsFactory, ClientsFactory clientsFactory) {
        this.repository = repository;
        this.loginsFactory = loginsFactory;
        this.clientsFactory = clientsFactory;
    }

    //--- Functions ----------------------------------------------------
    @Override
    public boolean createClientAndLogin(LoginsModel login) {
        return repository.createClientAndLogin(loginsFactory.createEntity(login));
    }

    @Override
    public ClientsModel findClientByUsername(String username) {
        return clientsFactory.createModel(repository.findClientByUsername(username));
    }

}
