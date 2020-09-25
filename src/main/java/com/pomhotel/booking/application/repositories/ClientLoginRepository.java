package com.pomhotel.booking.application.repositories;

import com.pomhotel.booking.application.domain.entities.ClientsEntity;
import com.pomhotel.booking.application.domain.entities.LoginsEntity;
import com.pomhotel.booking.application.domain.entities.RoomsEntity;
import com.pomhotel.booking.application.models.ClientsModel;
import com.pomhotel.booking.application.models.LoginsModel;

public interface ClientLoginRepository {
    boolean createNewClientAndLogin(ClientsEntity newclient, LoginsEntity newlogin);
    boolean createNewLoginAndUser_Old(LoginsEntity login);
    ClientsEntity findClientByUsername(String key);
    LoginsEntity findLoginByUsername(String key);
    ClientsEntity findClientById(long id);
}
