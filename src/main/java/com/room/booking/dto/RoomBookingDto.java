package com.room.booking.dto;

/**
 * Created by Alexey on 02.08.2017.
 */
public class RoomBookingDto {

    private String fromTime;
    private String toTime;
    private String month;
    private Integer day;
    private String emplLogin;
    private String roomName;

    public String getFromTime() {
        return fromTime;
    }

    public void setFromTime(String fromTime) {
        this.fromTime = fromTime;
    }

    public String getToTime() {
        return toTime;
    }

    public void setToTime(String toTime) {
        this.toTime = toTime;
    }

    public String getEmplLogin() {
        return emplLogin;
    }

    public void setEmplLogin(String emplLogin) {
        this.emplLogin = emplLogin;
    }

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
        return "RoomBookingDto{" +
                "fromTime='" + fromTime + '\'' +
                ", toTime='" + toTime + '\'' +
                ", month='" + month + '\'' +
                ", day=" + day +
                ", emplLogin='" + emplLogin + '\'' +
                ", roomName='" + roomName + '\'' +
                '}';
    }
}
