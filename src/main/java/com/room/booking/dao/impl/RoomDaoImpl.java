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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexey on 20.07.2017.
 */
public class RoomDaoImpl implements RoomDao {

    private static RoomDaoImpl roomDao = new RoomDaoImpl();
    private final String selectRoomByNum = "select * from room where name=?";
    private final String insertRoom = "insert into room(name,size) values(?,?)";
    private final String getAllBooksQuery = "select * from room";


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
            room.setName(resultSet.getString("name"));
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
            preparedStatement.setString(1,room.getName());
            preparedStatement.setInt(2,room.getSize());
            preparedStatement.execute();
        }catch (SQLException ex){
            throw new DaoException("Room was not save because of internal error!");
        }
    }

    @Override
    public List<Room> getAllRooms() {
        List<Room> rooms = new ArrayList<>();
        try(ConnectionProxy connectionProxy = EntityManager.getEntityManager().getConnection()){
            PreparedStatement preparedStatement = connectionProxy.createPreparedStatement(getAllBooksQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Room room = new Room();
                room.setName(resultSet.getString("name"));
                room.setSize(Integer.valueOf(resultSet.getString("size")));
                rooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }
}
