package com.room.booking.dao;

import com.room.booking.domain.RoomBooking;

import java.util.List;

/**
 * Created by Alexey on 02.08.2017.
 */
public interface RoomBookingDao {
    List<RoomBooking> getAllRoomBooking(Long roomId);

}
