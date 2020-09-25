package com.pomhotel.booking.application.services;

import com.pomhotel.booking.application.domain.entities.ClientsEntity;
import com.pomhotel.booking.application.domain.entities.LoginsEntity;
import com.pomhotel.booking.application.factories.ClientsFactory;
import com.pomhotel.booking.application.factories.LoginsFactory;
import com.pomhotel.booking.application.models.ClientsModel;
import com.pomhotel.booking.application.models.LoginsModel;
import com.pomhotel.booking.application.repositories.ClientLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientLoginServiceImplementation implements ClientLoginService {

    ClientLoginRepository repository;
    LoginsFactory loginsFactory;
    ClientsFactory clientsFactory;

    @Autowired
    public ClientLoginServiceImplementation(ClientLoginRepository repository, LoginsFactory loginsFactory, ClientsFactory clientsFactory) {
        this.repository = repository;
        this.loginsFactory = loginsFactory;
        this.clientsFactory = clientsFactory;
    }

    @Override
    public boolean createNewLoginAndUser(LoginsModel login) {
        //TODO: Bicicleta para refactorizar
        //Falta controlar a bajo nivel que todos los datos han sido introducidos correctamente, deberia salir fallo de db si hay mas de una clave unica igual = false
        return repository.createNewLoginAndUser_Old(loginsFactory.createEntity(login));
    }

    @Override
    public boolean createNewClientAndLogin(ClientsModel newclient, LoginsModel newlogin) {
        //TODO: Bicicleta para eliminar
        repository.createNewClientAndLogin(clientsFactory.createEntity(newclient), loginsFactory.createEntity(newlogin));
        return false;
    }
}
