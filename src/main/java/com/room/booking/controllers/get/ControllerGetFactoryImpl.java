package com.room.booking.controllers.get;

import com.room.booking.controllers.Controller;
import com.room.booking.controllers.ControllerFactory;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alexey on 19.07.2017.
 */
public class ControllerGetFactoryImpl implements ControllerFactory {

    private static ControllerFactory instance = new ControllerGetFactoryImpl();
    private Map<String, Controller> controllersMap = new HashMap<>();


    private ControllerGetFactoryImpl(){
        controllersMap.put("login", new LoginGetController());
        controllersMap.put("admin", new AdminGetController());
        controllersMap.put("addemployee", new AddEmployeeGetController());
        controllersMap.put("addroom",new AddRoomGetController());
//        controllersMap.put("", new IndexGetController());
        controllersMap.put("logout", new LogOutController());
        controllersMap.put("roombooking", new RoomBookingController());
        controllersMap.put("getAllRooms", new AllBooksGetController());
    }

    @Override
    public Controller getController(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String[] urls = uri.split("/");
        if (urls.length==0){
            return controllersMap.get("login");
        }
        //return last
        return controllersMap.get(urls[urls.length-1]);
    }


    public static ControllerFactory getInstance(){
        return instance;
    }

}
