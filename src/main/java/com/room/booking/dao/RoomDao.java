package com.room.booking.dao;

import com.room.booking.domain.Room;

import java.util.List;

/**
 * Created by Alexey on 20.07.2017.
 */
public interface RoomDao {

    Room getRoomByNumber(String number);
    void saveRoom(Room room);

    List<Room> getAllRooms();
}
