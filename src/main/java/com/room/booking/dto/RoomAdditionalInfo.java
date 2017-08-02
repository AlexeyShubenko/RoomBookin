package com.room.booking.dto;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Month;

/**
 * Created by Alexey on 01.08.2017.
 */
public class RoomAdditionalInfo {

    private String roomName;
    private String month;
    private Integer day;

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "RoomAdditionalInfo{" +
                "roomName='" + roomName + '\'' +
                ", month='" + month + '\'' +
                ", day=" + day +
                '}';
    }

    public static void checkEnteredDate(RoomAdditionalInfo additionalInfo) throws DateTimeException {
        LocalDate localDate = LocalDate.of(LocalDate.now().getYear(), Month.valueOf(additionalInfo.getMonth()), additionalInfo.getDay());
    }
}
