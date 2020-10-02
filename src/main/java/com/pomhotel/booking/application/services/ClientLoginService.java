package com.pomhotel.booking.application.services;

import com.pomhotel.booking.application.models.ClientsModel;
import com.pomhotel.booking.application.models.LoginsModel;

//--- Service Interface -------------------------------------------
public interface ClientLoginService {

    boolean createClientAndLogin(LoginsModel login);

    ClientsModel findClientByUsername(String username);

}
