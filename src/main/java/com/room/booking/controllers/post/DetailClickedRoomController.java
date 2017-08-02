package com.room.booking.controllers.post;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.room.booking.controllers.Controller;
import com.room.booking.dto.RoomAdditionalInfo;
import com.room.booking.dto.RoomBookingDto;
import com.room.booking.dto.RoomDto;
import com.room.booking.service.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;

/**
 * Created by Alexey on 02.08.2017.
 */
public class DetailClickedRoomController implements Controller {

    private ServiceFactory serviceFactory = ServiceFactory.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String addInfoJson = "";
        //Received json from request
        if(bufferedReader != null){
            addInfoJson = bufferedReader.readLine();
        }
        //json -> object
        RoomAdditionalInfo additionalInfo = mapper.readValue(addInfoJson,RoomAdditionalInfo.class);
        //if entered date is incorrect form
        try {
            RoomAdditionalInfo.checkEnteredDate(additionalInfo);
        }catch (DateTimeException ex){
            RequestDispatcher rd = request.getRequestDispatcher("/roombooking.jsp");
            try {
                rd.forward(request,response);
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }

        //Received all bookings of room
        List<RoomBookingDto> roomBookingDtos = serviceFactory.getRoomBookingService().allRoomBookingsOfRoom(additionalInfo);
        response.setContentType("application/json");

        //send data to client
        mapper.writeValue(response.getOutputStream(), roomBookingDtos);
//        }

    }

}
