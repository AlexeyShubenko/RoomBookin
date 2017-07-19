package com.room.booking.exceptions;

/**
 * Created by Alexey on 29.04.2017.
 */
public class UserAlreadyExistException extends RuntimeException {

    public UserAlreadyExistException(Exception e){
        super(e);
    }

}
