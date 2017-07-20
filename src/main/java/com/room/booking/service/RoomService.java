package com.room.booking.service;

import com.room.booking.domain.Room;

/**
 * Created by Alexey on 20.07.2017.
 */

public interface RoomService {

//    Room getRoomByNumber(Integer number);
    void saveRoom(String number, Integer size);

}
