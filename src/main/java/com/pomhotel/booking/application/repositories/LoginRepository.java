package com.pomhotel.booking.application.repositories;

import com.pomhotel.booking.application.domain.entities.ClientsEntity;
import com.pomhotel.booking.application.domain.entities.LoginsEntity;
<<<<<<< HEAD
import com.pomhotel.booking.application.models.LoginModel;

public interface LoginRepository {
    ClientsEntity authentification (LoginsEntity entity);
=======
import org.apache.juli.logging.Log;

import java.util.List;
import java.util.Set;

public interface LoginRepository {
    //TODO refactor findByUsername -> findByUserName
    LoginsEntity authentification (LoginsEntity entity);
    LoginsEntity findById(long id);
    LoginsEntity findByUsername (String username);
    List<LoginsEntity> findAll();
    void createNewLogin(LoginsEntity entity);
    void updateLogin(LoginsEntity entity);
    void deleteLogin(long id);
>>>>>>> e691301ea9dcd5309d22b7ec566f4c594966da92
}
