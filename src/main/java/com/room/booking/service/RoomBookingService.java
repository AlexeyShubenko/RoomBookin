package com.room.booking.service;

import com.room.booking.dto.RoomAdditionalInfo;
import com.room.booking.dto.RoomBookingDto;

import java.util.List;

/**
 * Created by Alexey on 02.08.2017.
 */
public interface RoomBookingService {

    List<RoomBookingDto> allRoomBookingsOfRoom(RoomAdditionalInfo additionalInfo);
    void saveRoomBooking(RoomBookingDto roomBookingDto);

}
