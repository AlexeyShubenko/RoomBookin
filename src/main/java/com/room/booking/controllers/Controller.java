package com.room.booking.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Alexey on 19.07.2017.
 */
public interface Controller {

    void execute(HttpServletRequest request, HttpServletResponse response) throws IOException;

}
