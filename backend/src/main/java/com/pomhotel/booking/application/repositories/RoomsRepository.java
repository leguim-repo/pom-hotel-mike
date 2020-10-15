package com.pomhotel.booking.application.repositories;

import com.pomhotel.booking.application.domain.entities.RoomsEntity;
import java.util.List;

//--- Repository Interface -------------------------------------------
public interface RoomsRepository {

    RoomsEntity findById(long id);

    List<RoomsEntity> findAll();

    void saveOrUpdate(RoomsEntity entity);

    void deleteById(long id);

    void delete(RoomsEntity entity);

    List<RoomsEntity> findApplyingFilter(int guests, int minPrice, int maxPrice, long idType);

}
