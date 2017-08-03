package com.room.booking.controllers.post;

import com.room.booking.controllers.Controller;
import com.room.booking.domain.Employee;
import com.room.booking.exceptions.DbException;
import com.room.booking.exceptions.EmployeeExistException;
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
public class AddEmployeePostController implements Controller {

    private ServiceFactory serviceFactory = ServiceFactory.getInstance();

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
        String name = request.getParameter("username");
        String login = request.getParameter("userlogin");
        String password = request.getParameter("userpassword");

        RequestDispatcher rd = request.getRequestDispatcher("/addemployee.jsp");
        //simple validation
        if(name.trim().equals("") || login.trim().equals("") || password.equals("".trim())){
            try {
                request.setAttribute("error", "Fields are empty!");
                rd.forward(request,response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //save in DB
        try {
            serviceFactory.getEmployeeService().saveEmployee(name,login,password);
        }catch (EmployeeExistException emplEx){
            try {
                request.setAttribute("error", emplEx.getMessage());
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
