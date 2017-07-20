package com.room.booking.exceptions;

/**
 * Created by Alexey on 29.04.2017.
 */
public class EmployeeExistException extends RuntimeException {

    public EmployeeExistException(String message){
        super(message);
    }

}
