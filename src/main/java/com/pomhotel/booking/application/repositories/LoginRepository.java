package com.pomhotel.booking.application.repositories;

import com.pomhotel.booking.application.domain.entities.ClientsEntity;
import com.pomhotel.booking.application.domain.entities.LoginsEntity;
import org.apache.juli.logging.Log;
import java.util.List;
import java.util.Set;
import com.pomhotel.booking.application.models.LoginModel;

public interface LoginRepository {
    //TODO esto va para el servicio. Para borrar
    ClientsEntity authentification (LoginsEntity entity);
    //TODO los find pendientes de ser eliminados
    LoginsEntity findById(long id);
    LoginsEntity findByEntity(LoginsEntity entity);
    List<LoginsEntity> findAll();

    void update(LoginsEntity entity);
    void deleteByEntity(LoginsEntity entity);
    void deleteById(long id);

    /*
    TODO Valorar pasar estas funciones al ClientRepository para petarnos todas las anteriores o bien
    pasar los metodos del ClientRepository a Login, ya que nuestro punto de acceso es Login
     */

    boolean checkLoginExists(LoginsEntity entity);
    boolean checkClientExists(ClientsEntity entity);

    void createNewLogin(LoginsEntity entity);
    boolean createNewLoginAndUser(LoginsEntity login, ClientsEntity client);


}
