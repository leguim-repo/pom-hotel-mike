package com.pomhotel.booking.application.domain.factories;

import com.pomhotel.booking.application.domain.entities.LoginsEntity;
import com.pomhotel.booking.application.models.LoginsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//--- Factory -------------------------------------------------------
@Component
public class LoginsFactory {

    //--- Needed Factories ------------------------------------------
    private ClientsFactory clientsFactory;

    //--- Constructor -----------------------------------------------
    @Autowired
    public LoginsFactory(ClientsFactory clientsFactory) {
        this.clientsFactory = clientsFactory;
    }

    //--- Functions -------------------------------------------------
    public LoginsEntity createEntity(LoginsModel model){
        LoginsEntity entity = new LoginsEntity();
        entity.setId(model.id);
        entity.setUsername(model.username);
        entity.setPassword(model.password);
        entity.setClientsByFkClientId(clientsFactory.createEntity(model.clientsByFkClientId));
        entity.setRole("ROLE_CLIENT");
        entity.setEnabled(true);
        return entity;
    }
    public LoginsModel createModel(LoginsEntity entity){
        LoginsModel model = new LoginsModel();
        model.id = entity.getId();
        model.username = entity.getUsername();
        model.password = entity.getPassword();
        model.clientsByFkClientId = clientsFactory.createModel(entity.getClientsByFkClientId());
        return model;
    }
}