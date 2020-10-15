package com.pomhotel.booking.application.services;

import com.pomhotel.booking.application.models.RoomsModel;
import java.util.List;

//--- Service Interface -------------------------------------------
public interface RoomsService {

    RoomsModel findById(long id);

    List<RoomsModel> findAll();

    List<RoomsModel> findApplyingFilter(int guests, int minPrice, int maxPrice, long idType);

    void saveOrUpdate(RoomsModel model);

    void deleteById(long id);

    void delete(RoomsModel model);

}
