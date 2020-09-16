package com.pomhotel.booking.application.services;

import com.pomhotel.booking.application.domain.entities.LoginsEntity;
import com.pomhotel.booking.application.models.ClientModel;
import com.pomhotel.booking.application.models.LoginModel;

public interface LoginService {
    ClientModel authentification (LoginModel model);
}
