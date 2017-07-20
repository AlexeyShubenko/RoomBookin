package com.room.booking.service.impl;

import com.room.booking.dao.RoomDao;
import com.room.booking.dao.impl.RoomDaoImpl;
import com.room.booking.domain.Room;
import com.room.booking.exceptions.RoomExistException;
import com.room.booking.service.RoomService;
import com.room.booking.service.utils.EntityManager;

import java.util.Objects;

/**
 * Created by Alexey on 20.07.2017.
 */
public class RoomServiceImpl implements RoomService {

    @Override
    public void saveRoom(String number, Integer size) {
        EntityManager.getEntityManager().beginTransaction();
        Room room = RoomDaoImpl.getRoomDao().getRoomByNumber(number);
        //if room exist
        if(Objects.isNull(room)){
            Room newRoom = new Room();
            newRoom.setNumber(number);
            newRoom.setSize(size);
            //else save
//            EntityManager.getEntityManager().beginTransaction();
            RoomDaoImpl.getRoomDao().saveRoom(newRoom);

        }else {
            throw new RoomExistException("Room already exist!");
        }
        EntityManager.getEntityManager().commit();
    }
}
