package com.pomhotel.booking.application.repositories;

import com.pomhotel.booking.application.domain.entities.ClientsEntity;
import com.pomhotel.booking.application.domain.entities.LoginsEntity;

import java.util.List;

public interface ClientRepository {

    // operaciones de comprobacion de login
    boolean checkLoginExists(LoginsEntity entity);
    boolean checkClientExists(ClientsEntity entity);

    // operacion para la creacion de nuevo login y cliente
    boolean createNewLoginAndClient(LoginsEntity login, ClientsEntity client);

    // operaciones de crud
    boolean create(ClientsEntity entity);
    boolean update(ClientsEntity entity);
    boolean delete(ClientsEntity entity);

    // metodos que pueden ser usados para tareas de administracion en una feature futura de la aplicacion
    List<ClientsEntity> findAll();
    ClientsEntity findById(long id);
    List<ClientsEntity> findByName(String cadena);
    List<ClientsEntity> findEmail(String cadena);

    void createNewLogin(LoginsEntity entity);

}
