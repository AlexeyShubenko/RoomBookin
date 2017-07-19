package com.room.booking.servlet;

import com.room.booking.controllers.Controller;
import com.room.booking.controllers.ControllerFactory;
import com.room.booking.controllers.get.ControllerGetFactoryImpl;
import com.room.booking.controllers.post.ControllerPostFactoryImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Alexey on 18.07.2017.
 */
public class DispatcherServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ControllerFactory factory = ControllerGetFactoryImpl.getInstance();
        Controller controller = factory.getController(req);
        controller.execute(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ControllerFactory factory = ControllerPostFactoryImpl.getInstance();
        Controller controller = factory.getController(req);
        controller.execute(req,resp);
    }
}
