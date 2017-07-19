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
        controllersMap.put("addemployee", new EmployeeRegistryPostController());
        controllersMap.put("addroom",new RoomRegistryPostController());
//        controllersMap.put("", new IndexGetController());
//        controllersMap.put("roombooking", new MainGetController());
    }

    @Override
    public Controller getController(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String[] urls = uri.split("/");
        System.out.println(urls[urls.length-1]);
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
