package com.room.booking.dto;

import com.room.booking.domain.Room;

/**
 * Created by Alexey on 19.07.2017.
 */
public class RoomDto {

    private String name;
    private Integer size;

    public RoomDto() {
    }

    public RoomDto( String name, Integer size) {
        this.name = name;
        this.size = size;
    }

    public static class Builder{
        RoomDto roomDto = new RoomDto();

        public Builder setNumber(Room room){
            roomDto.setName(room.getName());
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

    public String getName() {
        return name;
    }

    public void setName(String number) {
        this.name = number;
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
                ", number=" + name +
                ", size=" + size +
                '}';
    }
}
