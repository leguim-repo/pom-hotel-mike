package com.pomhotel.booking.application.services;

import com.pomhotel.booking.application.models.ClientsModel;
import java.util.List;

public interface ClientService {
    void Insert(ClientsModel employee);
    ClientsModel findById(long id);
    List<ClientsModel> findAll();
    void saveOrUpdate(ClientsModel model);
    void deleteById(long id);
    void delete(ClientsModel model);
}
