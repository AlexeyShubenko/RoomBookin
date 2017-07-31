package com.room.booking.controllers.get;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.room.booking.controllers.Controller;
import com.room.booking.domain.Employee;
import com.room.booking.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;

/**
 * Created by Alexey on 31.07.2017.
 */
public class RoomBookingController implements Controller {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        Employee employee = (Employee) session.getAttribute("employee");
        if(Objects.nonNull(employee)){
            try {
                response.sendRedirect("/roombooking.jsp");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            try {
                response.sendRedirect("/login.jsp");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
