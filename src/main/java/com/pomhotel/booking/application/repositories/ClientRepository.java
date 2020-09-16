package com.pomhotel.booking.application.repositories;

import com.pomhotel.booking.application.domain.entities.ClientsEntity;

import java.util.List;

public interface ClientRepository {
    //TODO los find pendientes de ser eliminados
    ClientsEntity findById(long id);
    ClientsEntity findByNombre (ClientsEntity entity);
    List<ClientsEntity> findAll();
    void createClient(ClientsEntity entity);
    void updateClient(ClientsEntity entity);
    void deleteClient(long id);
}
