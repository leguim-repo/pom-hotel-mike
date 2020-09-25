package com.pomhotel.booking.application.repositories;

import com.pomhotel.booking.application.domain.entities.ClientsEntity;
import com.pomhotel.booking.application.domain.entities.LoginsEntity;

public interface ClientLoginRepository {
    boolean createNewLoginAndUser(LoginsEntity login, ClientsEntity client);
}
