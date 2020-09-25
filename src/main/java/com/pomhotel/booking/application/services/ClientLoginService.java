package com.pomhotel.booking.application.services;

import com.pomhotel.booking.application.models.ClientsModel;
import com.pomhotel.booking.application.models.LoginsModel;

public interface ClientLoginService {
    boolean createNewLoginAndUser(LoginsModel login);
}
