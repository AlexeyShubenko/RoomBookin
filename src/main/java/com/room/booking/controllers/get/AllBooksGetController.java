package com.room.booking.controllers.get;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.room.booking.controllers.Controller;
import com.room.booking.dto.RoomDto;
import com.room.booking.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Alexey on 31.07.2017.
 */
public class AllBooksGetController implements Controller {

    private ServiceFactory serviceFactory = ServiceFactory.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        //Received all rooms from database
        List<RoomDto> rooms = serviceFactory.getRoomService().getAllRooms();
        response.setContentType("application/json");
        //send data to client
        mapper.writeValue(response.getOutputStream(),rooms);
    }

}
