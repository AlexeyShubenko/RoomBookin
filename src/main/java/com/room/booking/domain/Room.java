package com.room.booking.domain;

import com.room.booking.dto.RoomDto;

/**
 * Created by Alexey on 19.07.2017.
 */
public class Room {

    private Long id;
    private String number;
    private Integer size;

    public Room() {
    }

    public Room(Long id, String number, Integer size) {
        this.id = id;
        this.number = number;
        this.size = size;
    }

    public static class Builder{

        Room room = new Room();

        public Builder setNumber(RoomDto roomDto){
            room.setNumber(roomDto.getNumber());
            return this;
        }

        public Builder setSize(RoomDto roomDto){
            room.setSize(roomDto.getSize());
            return this;
        }

        public Room build(){
            return room;
        }

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
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
                "id=" + id +
                ", number=" + number +
                ", size=" + size +
                '}';
    }
}
