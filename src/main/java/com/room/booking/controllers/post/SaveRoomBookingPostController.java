package com.room.booking.controllers.post;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.room.booking.controllers.Controller;
import com.room.booking.domain.Employee;
import com.room.booking.dto.RoomBookingDto;
import com.room.booking.exceptions.RoomAlreadyBookedOnThisTimeException;
import com.room.booking.service.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

/**
 * Created by Alexey on 02.08.2017.
 */
public class SaveRoomBookingPostController implements Controller {

    private ServiceFactory serviceFactory = ServiceFactory.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Employee employee = (Employee) session.getAttribute("employee");

        if(Objects.nonNull(employee)){

            ObjectMapper mapper = new ObjectMapper();
            String roomBookingJson = "";
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(request.getInputStream()));
            if (bufferedReader != null) {
                roomBookingJson = bufferedReader.readLine();
            }
            //receive room booking object
            RoomBookingDto roomBookingDto = mapper.readValue(roomBookingJson, RoomBookingDto.class);
            roomBookingDto.setEmplLogin(employee.getLogin());
            try{
                serviceFactory.getRoomBookingService().saveRoomBooking(roomBookingDto);
            }catch (RoomAlreadyBookedOnThisTimeException ex){
                RequestDispatcher rd = request.getRequestDispatcher("/roombooking.jsp");
                try {
                    rd.forward(request,response);
                } catch (ServletException e) {
                    e.printStackTrace();
                }
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
