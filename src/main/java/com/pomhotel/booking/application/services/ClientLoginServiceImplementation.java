package com.pomhotel.booking.application.services;

import com.pomhotel.booking.application.factories.LoginsFactory;
import com.pomhotel.booking.application.models.LoginsModel;
import com.pomhotel.booking.application.repositories.ClientLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientLoginServiceImplementation implements ClientLoginService {

    ClientLoginRepository repository;
    LoginsFactory loginsFactory;

    @Autowired
    public ClientLoginServiceImplementation(ClientLoginRepository repository, LoginsFactory loginsFactory) {
        this.repository = repository;
        this.loginsFactory = loginsFactory;
    }

    @Override
    public boolean createClientAndLogin(LoginsModel login) {
        return repository.createClientAndLogin(loginsFactory.createEntity(login));
    }
}
