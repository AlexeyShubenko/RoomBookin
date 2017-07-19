package com.room.booking.controllers;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Alexey on 19.07.2017.
 */
public interface ControllerFactory {

    Controller getController(HttpServletRequest request);

}
