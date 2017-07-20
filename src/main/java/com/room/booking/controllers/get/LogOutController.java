package com.room.booking.controllers.get;

import com.room.booking.controllers.Controller;
import com.room.booking.domain.Employee;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

/**
 * Created by Alexey on 20.07.2017.
 */
public class LogOutController implements Controller{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.invalidate();
        try {
            response.sendRedirect("/login.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
//        Employee admin = (Employee) session.getAttribute("admin");
//        Employee user = (Employee) session.getAttribute("user");
//        if(Objects.nonNull(admin)){
//            session.removeAttribute("admin");
//        }
//        if(Objects.nonNull(user)){
//            session.removeAttribute("user");
//        }
    }

}
