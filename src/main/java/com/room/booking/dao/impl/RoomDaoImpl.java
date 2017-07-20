package com.room.booking.dao.impl;

import com.room.booking.dao.RoomDao;
import com.room.booking.domain.Room;
import com.room.booking.exceptions.DaoException;
import com.room.booking.exceptions.DbException;
import com.room.booking.service.utils.ConnectionProxy;
import com.room.booking.service.utils.EntityManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Alexey on 20.07.2017.
 */
public class RoomDaoImpl implements RoomDao {

    private static RoomDaoImpl roomDao = new RoomDaoImpl();
    private String selectRoomByNum = "select * from room where num=?";
    private final static String insertRoom = "insert into room(num,size) values(?,?)";


    private RoomDaoImpl(){}
    public static RoomDaoImpl getRoomDao() {
        return roomDao;
    }

    @Override
    public Room getRoomByNumber(String number) {
        Room room = null;
        try(ConnectionProxy connectionProxy = EntityManager.getEntityManager().getConnection()){
        PreparedStatement preparedStatement = connectionProxy.createPreparedStatement(selectRoomByNum);
        preparedStatement.setString(1,number);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            room = new Room();
            room.setNumber(resultSet.getString("num"));
            room.setSize(Integer.valueOf(resultSet.getString("size")));
        }
        }catch (SQLException ex){
            throw new DaoException(ex.getMessage());
        }
        return room;
    }

    @Override
    public void saveRoom(Room room) {
        try(ConnectionProxy connectionProxy = EntityManager.getEntityManager().getConnection()){
            PreparedStatement preparedStatement = connectionProxy.createPreparedStatement(insertRoom);
            preparedStatement.setString(1,room.getNumber());
            preparedStatement.setInt(2,room.getSize());
            preparedStatement.execute();
        }catch (SQLException ex){
            throw new DaoException("Room was not save because of internal error!");
        }
    }
}
