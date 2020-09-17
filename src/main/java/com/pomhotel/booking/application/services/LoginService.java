package com.pomhotel.booking.application.services;

import com.pomhotel.booking.application.models.ClientsModel;
import com.pomhotel.booking.application.models.LoginsModel;
import com.pomhotel.booking.application.models.RoomtypesModel;

import java.util.List;

public interface LoginService {
    List<LoginsModel> findAll();
    ClientsModel authentification (LoginsModel model);
}
