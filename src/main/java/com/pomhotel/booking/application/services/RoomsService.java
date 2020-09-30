package com.pomhotel.booking.application.services;

import com.pomhotel.booking.application.domain.entities.RoomsEntity;
import com.pomhotel.booking.application.models.RoomsModel;

import java.util.List;

public interface RoomsService {
    RoomsModel findById(long id);
    List<RoomsModel> findAll();
    void saveOrUpdate(RoomsModel model);
    void deleteById(long id);
    void delete(RoomsModel model);
    List<RoomsModel> findApplyingFilter();
}
