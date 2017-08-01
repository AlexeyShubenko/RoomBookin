package com.room.booking.dto;

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
}
