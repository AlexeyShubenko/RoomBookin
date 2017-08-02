package com.room.booking.dao;

import com.room.booking.domain.RoomBooking;
import com.room.booking.dto.RoomBookingDto;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Alexey on 02.08.2017.
 */
public interface RoomBookingDao {
    List<RoomBooking> getAllRoomBooking(Long roomId);

    void saveRoomBooking(Long room_id, Long empl_id, LocalDateTime fromTime, LocalDateTime toTime);
}
