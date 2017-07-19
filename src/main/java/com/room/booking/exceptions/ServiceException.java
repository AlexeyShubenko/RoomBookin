package com.room.booking.exceptions;

/**
 * Created by Alexey on 29.04.2017.
 */
public class ServiceException extends RuntimeException {

    public ServiceException(Exception e){
        super(e);
    }

}
