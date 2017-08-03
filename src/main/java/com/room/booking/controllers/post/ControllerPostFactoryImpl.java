package com.room.booking.controllers.post;

import com.room.booking.controllers.Controller;
import com.room.booking.controllers.ControllerFactory;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alexey on 19.07.2017.
 */
public class ControllerPostFactoryImpl implements ControllerFactory {

    private static ControllerFactory instance = new ControllerPostFactoryImpl();
    private Map<String, Controller> controllersMap = new HashMap<>();


    private ControllerPostFactoryImpl(){
        controllersMap.put("login", new LoginPostController());
        controllersMap.put("addemployee", new AddEmployeePostController());
        controllersMap.put("addroom",new AddRoomPostController());
        controllersMap.put("getDetailOfClickedRoom", new DetailClickedRoomController());
        controllersMap.put("saveRoomBooking", new SaveRoomBookingPostController());
}

    @Override
    public Controller getController(HttpServletRequest request) {
        //TODO if url not exist
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
