package com.room.booking.service.impl;

import com.room.booking.dao.impl.RoomBookingDaoImpl;
import com.room.booking.dao.impl.RoomDaoImpl;
import com.room.booking.domain.Room;
import com.room.booking.domain.RoomBooking;
import com.room.booking.dto.RoomAdditionalInfo;
import com.room.booking.dto.RoomBookingDto;
import com.room.booking.service.RoomBookingService;
import com.room.booking.service.RoomService;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexey on 02.08.2017.
 */
public class RoomBookingServiceImpl implements RoomBookingService {

    @Override
    public List<RoomBookingDto> allRoomBookingsOfRoom(RoomAdditionalInfo additionalInfo) {
        //get room by name
        Room room = RoomDaoImpl.getRoomDao().getRoomByName(additionalInfo.getRoomName());
        List<RoomBooking> allRoomBooking = RoomBookingDaoImpl.getInstance().getAllRoomBooking(room.getId());

        List<RoomBookingDto> roomBookingsOfClickedRoom = new ArrayList<>();
        for (RoomBooking roomBooking : allRoomBooking) {
            Integer day = roomBooking.getFromTime().getDayOfMonth();
            Month month = roomBooking.getFromTime().getMonth();

            //choose all bookings of chosen date
            if(day==additionalInfo.getDay() && month.toString().equals(additionalInfo.getMonth())){
                RoomBookingDto roomBookingDto = new RoomBookingDto();
                roomBookingDto.setFromTime(roomBooking.getFromTime().toLocalTime().toString());
                roomBookingDto.setToTime(roomBooking.getToTime().toLocalTime().toString());
                //add all bookings by date
                roomBookingsOfClickedRoom.add(roomBookingDto);
            }

        }
        return roomBookingsOfClickedRoom;
    }

}
