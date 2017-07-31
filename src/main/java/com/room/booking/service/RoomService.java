package com.room.booking.service;

import com.room.booking.domain.Room;
import com.room.booking.dto.RoomDto;

import java.util.List;

/**
 * Created by Alexey on 20.07.2017.
 */

public interface RoomService {

//    Room getRoomByNumber(Integer number);
    void saveRoom(String number, Integer size);

    List<RoomDto> getAllRooms();
}
