package com.room.booking.domain;

import java.time.LocalDateTime;

/**
 * Created by Alexey on 19.07.2017.
 */
public class RoomBooking {

    private Long id;
    private Long room_id;
    private Long empl_id;
    private LocalDateTime fromTime;
    private LocalDateTime toTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoom_id() {
        return room_id;
    }

    public void setRoom_id(Long room_id) {
        this.room_id = room_id;
    }

    public Long getEmpl_id() {
        return empl_id;
    }

    public void setEmpl_id(Long empl_id) {
        this.empl_id = empl_id;
    }

    public LocalDateTime getFromTime() {
        return fromTime;
    }

    public void setFromTime(LocalDateTime fromTime) {
        this.fromTime = fromTime;
    }

    public LocalDateTime getToTime() {
        return toTime;
    }

    public void setToTime(LocalDateTime toTime) {
        this.toTime = toTime;
    }

    @Override
    public String toString() {
        return "RoomBooking{" +
                "id=" + id +
                ", room_id=" + room_id +
                ", empl_id=" + empl_id +
                ", fromTime=" + fromTime +
                ", toTime=" + toTime +
                '}';
    }
}
