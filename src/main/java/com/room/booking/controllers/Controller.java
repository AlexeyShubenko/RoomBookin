package com.room.booking.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Alexey on 19.07.2017.
 */
public interface Controller {

    void execute(HttpServletRequest request, HttpServletResponse response);

}
