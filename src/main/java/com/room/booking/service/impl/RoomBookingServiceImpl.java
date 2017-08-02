package com.room.booking.service.impl;

import com.room.booking.dao.impl.EmployeeDaoImpl;
import com.room.booking.dao.impl.RoomBookingDaoImpl;
import com.room.booking.dao.impl.RoomDaoImpl;
import com.room.booking.domain.Employee;
import com.room.booking.domain.Room;
import com.room.booking.domain.RoomBooking;
import com.room.booking.dto.RoomAdditionalInfo;
import com.room.booking.dto.RoomBookingDto;
import com.room.booking.exceptions.DaoException;
import com.room.booking.exceptions.RoomAlreadyBookedOnThisTimeException;
import com.room.booking.service.RoomBookingService;
import com.room.booking.service.RoomService;
import com.room.booking.service.utils.EntityManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

    @Override
    public void saveRoomBooking(RoomBookingDto roomBookingDto) {
        try {
            EntityManager.getEntityManager().beginTransaction();
            //receive room and employee for booking
            Room room = RoomDaoImpl.getRoomDao().getRoomByName(roomBookingDto.getRoomName());
            Employee employee = EmployeeDaoImpl.getInstance().getEmployeeByLogin(roomBookingDto.getEmplLogin());
            System.out.println(employee.toString());
            List<RoomBooking> allRoomBookings = RoomBookingDaoImpl.getInstance().getAllRoomBooking(room.getId());

            List<RoomBooking> allRoomBookingsInEnteredTime = new ArrayList<>();
            //проходимся по всем забронированым временам комнаты
            //и находим все бронирования для даной даты
            for (RoomBooking roomBooking : allRoomBookings) {
                Integer day = roomBooking.getFromTime().getDayOfMonth();
                Month month = roomBooking.getFromTime().getMonth();

                //choose all bookings of chosen date
                //нашли время бронирования комнаты у выбраные день и месяц
                if (day == roomBookingDto.getDay() && month.toString().equals(roomBookingDto.getMonth())) {
                    //true - если нашли день обьект roombooking того дня который выбрали
                    allRoomBookingsInEnteredTime.add(roomBooking);
                }
            }

            boolean isBooked = false;
            for (RoomBooking roomBooking : allRoomBookingsInEnteredTime) {
                //время начала брони
                LocalTime timeFrom = LocalTime.parse(roomBookingDto.getFromTime());
                //время конца брони
                LocalTime timeTo = LocalTime.parse(roomBookingDto.getToTime());

                LocalTime existFromTime = roomBooking.getFromTime().toLocalTime();
                LocalTime existToTime = roomBooking.getToTime().toLocalTime();

                //эсли время fromTime или totime находиться в диапазоне какого-то забронированого времени
                // то указываем время как забронированое
                if ((existFromTime.isBefore(timeFrom)
                        && existToTime.isAfter(timeFrom))
                        ||
                        (existFromTime.isBefore(timeTo)
                                && existToTime.isAfter(timeTo))
                        ) {
                    //true - эсли timeFrom или timeTo входит в диапазон забронированого времени
                    isBooked = true;
                    break;
                }
                //проверяеться или fromTime или totime имеет время равное существующему
                if((existFromTime.equals(timeFrom)
                        ||existToTime.equals(timeFrom))
                    ||
                    (existFromTime.equals(timeTo)
                        ||(existToTime.equals(timeTo)))
                        ){
                    //true - эсли timeFrom или timeTo имеет значение одинаковое как у забронированого времени
                    isBooked = true;
                    break;
                }
                //если введеный диапазон времени поглощает уже существующий диапазон времени
                if((timeFrom.isBefore(existFromTime) && timeTo.isAfter(existToTime)) ){
                    //true - если введеный диапазон времени поглощает уже существующий диапазон времени
                    isBooked = true;
                    break;
                }
            }

            if (isBooked) {
                throw new RoomAlreadyBookedOnThisTimeException();
            } else {
                //формируем время fromTime как LocalDateTime
                LocalTime localTimeFrom = LocalTime.parse(roomBookingDto.getFromTime());
                LocalDateTime fromTime = LocalDateTime.of(LocalDate.now().getYear(),
                        Month.valueOf(roomBookingDto.getMonth()),
                        roomBookingDto.getDay(),
                        localTimeFrom.getHour(),
                        localTimeFrom.getMinute());
                //формируем время toTime как LocalDateTime
                LocalTime localTimeTo = LocalTime.parse(roomBookingDto.getToTime());
                LocalDateTime toTime = LocalDateTime.of(LocalDate.now().getYear(),
                        Month.valueOf(roomBookingDto.getMonth()),
                        roomBookingDto.getDay(),
                        localTimeTo.getHour(),
                        localTimeTo.getMinute());
                RoomBookingDaoImpl.getInstance().saveRoomBooking(room.getId(), employee.getId(), fromTime, toTime);
            }
            EntityManager.getEntityManager().commit();
            isBooked=false;
        }catch (DaoException ex){
            EntityManager.getEntityManager().rollback();
        }
    }

}
