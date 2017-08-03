package com.room.booking.controllers.post;

import com.room.booking.controllers.Controller;
import com.room.booking.domain.Employee;
import com.room.booking.domain.Role;
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
public class LoginPostController implements Controller {

    private ServiceFactory serviceFactory = ServiceFactory.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter("userlogin");
        String password = request.getParameter("userpassword");
        if(login.equals("") || password.equals("")){
            request.setAttribute("error","Fields are empty!");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login.jsp");
            try {
                requestDispatcher.forward(request,response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //TODO validation

        Employee employee= serviceFactory.getLoginService().verifyEmployee(login, password);
        if(Objects.nonNull(employee)){
            //if employee is ADMIN
            if(employee.getRole()== Role.ADMIN) {
                try {
                    HttpSession session = request.getSession(true);
                    session.setAttribute("admin", employee);
                    response.sendRedirect("/admin");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                //if employee is USER
                try {
                    HttpSession session = request.getSession();
                    session.setAttribute("employee", employee);
                    response.sendRedirect("/roombooking");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else {
            request.setAttribute("error","Employee not exist!");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login.jsp");
            try {
                requestDispatcher.forward(request,response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
