package com.room.booking.controllers.post;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.room.booking.controllers.Controller;
import com.room.booking.dto.RoomDto;
import com.room.booking.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by Alexey on 31.07.2017.
 */
public class DetailRoomInfoController implements Controller{

    private ServiceFactory serviceFactory;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String roomJson = "";
        //Received json from request
        if(bufferedReader != null){
            roomJson = bufferedReader.readLine();
        }
        //json -> object
        RoomDto roomDto = mapper.readValue(roomJson,RoomDto.class);
        System.out.println(roomDto.toString());
        //Received all booking of room
//        List<RoomDto> rooms = serviceFactory.getInstance().getRoomService().getAllRooms();
        response.setContentType("application/json");
        //send data to client
//        mapper.writeValue(response.getOutputStream(),rooms);
    }

}
