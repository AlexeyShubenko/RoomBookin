package com.room.booking.exceptions;

/**
 * Created by Alexey on 29.04.2017.
 */
public class DaoException extends RuntimeException {

    public DaoException(Exception e){
        super(e);
    }

}
