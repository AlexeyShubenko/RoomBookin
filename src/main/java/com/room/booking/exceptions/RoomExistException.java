package com.room.booking.exceptions;

/**
 * Created by Alexey on 20.07.2017.
 */
public class RoomExistException extends RuntimeException {

    public RoomExistException(String message) {
        super(message);
    }
}
