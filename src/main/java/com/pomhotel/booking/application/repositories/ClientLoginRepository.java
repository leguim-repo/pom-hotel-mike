package com.pomhotel.booking.application.repositories;

import com.pomhotel.booking.application.domain.entities.ClientsEntity;
import com.pomhotel.booking.application.domain.entities.LoginsEntity;

//--- Repository Interface -------------------------------------------
public interface ClientLoginRepository {

    boolean createClientAndLogin(LoginsEntity login);

    ClientsEntity findClientByUsername(String key);

    LoginsEntity findLoginByUsername(String key);

    ClientsEntity findClientById(long id);

}
