package com.pomhotel.booking.application.factories;

import com.pomhotel.booking.application.domain.entities.LoginsEntity;
import com.pomhotel.booking.application.models.LoginModel;
import org.springframework.stereotype.Component;

@Component
public class LoginsFactory {
    public LoginsEntity createEntity(LoginModel model){
        LoginsEntity entity = new LoginsEntity();
        entity.setId(model.id);
        entity.setUsername(model.username);
        entity.setPassword(model.password);
        entity.setClientsByFkClientId(model.clientsByFkClientId);
        return entity;
    }
    public LoginModel createModel(LoginsEntity entity){
        LoginModel model = new LoginModel();
        model.id = entity.getId();
        model.username = entity.getUsername();
        model.password = entity.getPassword();
        model.clientsByFkClientId = entity.getClientsByFkClientId();
        return model;
    }
}
