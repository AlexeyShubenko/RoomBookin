package com.room.booking.controllers.post;

import com.room.booking.controllers.Controller;
import com.room.booking.domain.Employee;
import com.room.booking.domain.RegEx;
import com.room.booking.domain.Role;
import com.room.booking.exceptions.DbException;
import com.room.booking.exceptions.RoomExistException;
import com.room.booking.service.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

/**
 * Created by Alexey on 19.07.2017.
 */
public class AddRoomPostController implements Controller {

    private ServiceFactory serviceFactory;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Employee admin = (Employee) session.getAttribute("admin");
        if(Objects.isNull(admin)){
            try {
                response.sendRedirect("/login.jsp");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String number = request.getParameter("roomnumber");
        String sizeValue = request.getParameter("roomsize");
        RequestDispatcher rd = request.getRequestDispatcher("/addroom.jsp");
        //simple validation
        if(number.trim().equals("") || sizeValue.trim().equals("")){
            try {
                request.setAttribute("error", "Fields are empty!");
                rd.forward(request,response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //if not a number
        if(!RegEx.isNumber(sizeValue)){
            try {
                request.setAttribute("error", "Field room size is not a number!");
                rd.forward(request,response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        Integer size = Integer.valueOf(sizeValue);
        //save in DB
        try {
            ServiceFactory.getInstance().getRoomService().saveRoom(number, size);
        }catch (RoomExistException roomEx){
            try {
                request.setAttribute("error", roomEx.getMessage());
                rd.forward(request,response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }catch (DbException dbEx){
            try {
                request.setAttribute("error", dbEx.getMessage());
                rd.forward(request,response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            ///if all is OK, user is registered, we don`t have any Attributes, can do redirect
            response.sendRedirect("/admin.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
