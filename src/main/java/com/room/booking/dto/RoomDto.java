package com.room.booking.dto;

import com.room.booking.domain.Room;

/**
 * Created by Alexey on 19.07.2017.
 */
public class RoomDto {

    private Integer number;
    private Integer size;

    public RoomDto() {
    }

    public RoomDto( Integer number, Integer size) {
        this.number = number;
        this.size = size;
    }

    public static class Builder{

        RoomDto roomDto = new RoomDto();

        public Builder setNumber(Room room){
            roomDto.setNumber(room.getNumber());
            return this;
        }

        public Builder setSize(Room room){
            roomDto.setSize(room.getSize());
            return this;
        }

        public RoomDto build(){
            return roomDto;
        }

    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Room{" +
                ", number=" + number +
                ", size=" + size +
                '}';
    }
}
