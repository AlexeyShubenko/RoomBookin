package com.room.booking.dao.impl;

import com.room.booking.dao.RoomBookingDao;
import com.room.booking.domain.RoomBooking;
import com.room.booking.dto.RoomBookingDto;
import com.room.booking.exceptions.DaoException;
import com.room.booking.service.utils.ConnectionProxy;
import com.room.booking.service.utils.EntityManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexey on 02.08.2017.
 */
public class RoomBookingDaoImpl implements RoomBookingDao {

    private static RoomBookingDaoImpl instance = new RoomBookingDaoImpl();
    private final String getAllRoomBookingByRoomNameQuery = "select * from roombooking where room_id=?";
    private final String insertRoomBookingQuery = "insert into roombooking (room_id,empl_id,fromtime,totime) values(?,?,?,?)";



    private RoomBookingDaoImpl(){}

    public static RoomBookingDaoImpl getInstance(){
        return instance;
    }

    @Override
    public List<RoomBooking> getAllRoomBooking(Long roomId) {
        List<RoomBooking> roomBookings = new ArrayList<>();
        try(ConnectionProxy connectionProxy = EntityManager.getEntityManager().getConnection()) {
            PreparedStatement preparedStatement = connectionProxy.createPreparedStatement(getAllRoomBookingByRoomNameQuery);
            preparedStatement.setLong(1,roomId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                RoomBooking roomBooking = new RoomBooking();
                roomBooking.setFromTime(resultSet.getTimestamp("fromtime").toLocalDateTime());
                roomBooking.setToTime(resultSet.getTimestamp("totime").toLocalDateTime());
                roomBooking.setEmpl_id(resultSet.getLong("empl_id"));
                roomBooking.setRoom_id(roomId);
                roomBooking.setId(resultSet.getLong("id"));
                //add all bookings of room by id
                roomBookings.add(roomBooking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return roomBookings;
    }

    @Override
    public void saveRoomBooking(Long room_id, Long empl_id, LocalDateTime fromTime, LocalDateTime toTime) {
        try(ConnectionProxy connectionProxy = EntityManager.getEntityManager().getConnection()){
            PreparedStatement preparedStatement = connectionProxy.createPreparedStatement(insertRoomBookingQuery);
            preparedStatement.setLong(1,room_id);
            preparedStatement.setLong(2,empl_id);
            preparedStatement.setTimestamp(3, Timestamp.valueOf(fromTime));
            preparedStatement.setTimestamp(4, Timestamp.valueOf(toTime));
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DaoException("Employee was not save because of internal error!");
        }
    }
}
