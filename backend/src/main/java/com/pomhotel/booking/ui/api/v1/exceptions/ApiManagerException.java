package com.pomhotel.booking.ui.api.v1.exceptions;

import com.pomhotel.booking.ui.api.v1.dto.BookingApiDTO;

import java.io.Serializable;

public class ApiManagerException extends RuntimeException implements Serializable {
    private ApiManagerException(Exception e, String errorMessage) {
        super(errorMessage, e);
    }

    public static ApiManagerException RoomNotFoundById(Exception e, String id) {
        return new ApiManagerException(e, "RoomNotFoundById. Room id = " + id + " NOT FOUND");
    }
    public static ApiManagerException BookingApiException(Exception e, BookingApiDTO dto) {
        return new ApiManagerException(e, "BookingApiDTO Malformed dto = " + dto.toString());
    }
    public static ApiManagerException NotFoundGetAllReservedDatesByRoomIdApi(Exception e, String id) {
        return new ApiManagerException(e, "NotFoundGetAllReservedDatesByRoomIdApi id = " + id + " NOT FOUND");
    }

    public static ApiManagerException BookRoomNowException(Exception e, BookingApiDTO dto) {
        return new ApiManagerException(e, "BookRoomNow Malformed dto = " + dto.toString());
    }


}


