package com.room.booking.service.impl;

import com.room.booking.dao.RoomDao;
import com.room.booking.dao.impl.RoomDaoImpl;
import com.room.booking.domain.Room;
import com.room.booking.dto.RoomDto;
import com.room.booking.exceptions.DaoException;
import com.room.booking.exceptions.DbException;
import com.room.booking.exceptions.RoomExistException;
import com.room.booking.service.RoomService;
import com.room.booking.service.utils.EntityManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Alexey on 20.07.2017.
 */
public class RoomServiceImpl implements RoomService {

    @Override
    public void saveRoom(String name, Integer size) {
        try {
            EntityManager.getEntityManager().beginTransaction();
            Room room = RoomDaoImpl.getRoomDao().getRoomByName(name);
            //if room exist
            if (Objects.isNull(room)) {
                Room newRoom = new Room();
                newRoom.setName(name);
                newRoom.setSize(size);
                //else save
                RoomDaoImpl.getRoomDao().saveRoom(newRoom);
            } else {
                throw new RoomExistException("Room already exist!");
            }
            EntityManager.getEntityManager().commit();
        }catch (DaoException ex){
            EntityManager.getEntityManager().rollback();
            throw new DbException(ex.getMessage());
        }
    }

    @Override
    public List<RoomDto> getAllRooms() {
        List<Room> rooms = RoomDaoImpl.getRoomDao().getAllRooms();

        List<RoomDto> roomDtos = new ArrayList<>();
        if (rooms.size()>0){
            for (Room room:rooms) {
                RoomDto roomDto = new RoomDto();
                roomDto.setName(room.getName());
                roomDto.setSize(room.getSize());
                roomDtos.add(roomDto);
            }
        }
        return roomDtos;
    }
}
