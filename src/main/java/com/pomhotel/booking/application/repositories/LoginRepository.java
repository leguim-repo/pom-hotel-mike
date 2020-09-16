package com.pomhotel.booking.application.repositories;

import com.pomhotel.booking.application.domain.entities.ClientsEntity;
import com.pomhotel.booking.application.domain.entities.LoginsEntity;
import org.apache.juli.logging.Log;
import java.util.List;
import java.util.Set;
import com.pomhotel.booking.application.models.LoginModel;

public interface LoginRepository {
    //TODO refactor findByUsername -> findByUserName
    ClientsEntity authentification (LoginsEntity entity);
    LoginsEntity findById(long id);
    LoginsEntity findByUsername (LoginsEntity entity);
    List<LoginsEntity> findAll();
    void createNewLogin(LoginsEntity entity);
    void updateLogin(LoginsEntity entity);
    void deleteLogin(long id);
}
