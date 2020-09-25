package com.pomhotel.booking.application.repositories;

import com.pomhotel.booking.application.domain.entities.ClientsEntity;
import com.pomhotel.booking.application.domain.entities.LoginsEntity;
import com.pomhotel.booking.application.domain.entities.RoomsEntity;

public interface ClientLoginRepository {
    boolean createNewLoginAndUser(LoginsEntity login);
    ClientsEntity findClientByUsername(String key);
    LoginsEntity findLoginByUsername(String key);
}
