package com.room.booking.exceptions;

/**
 * Created by Alexey on 20.07.2017.
 */
public class DbException extends RuntimeException {

    public DbException(String message) {
        super(message);
    }
}
