package com.pomhotel.booking.application.services;

import com.pomhotel.booking.application.models.RoomWithBookedDatesModel;

import java.util.List;

public interface RoomsAndBookedDatesService {
    List<RoomWithBookedDatesModel> findAllRoomsAndBookedDates();
}
