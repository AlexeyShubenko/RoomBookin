package com.room.booking.exceptions;

/**
 * Created by Alexey on 29.04.2017.
 */
public class UserExistException extends RuntimeException {

    public UserExistException(Exception ex){
        super(ex);
    }

}
