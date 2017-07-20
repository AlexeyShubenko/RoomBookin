package com.room.booking.service;

import com.room.booking.service.impl.LoginServiceImpl;
import com.room.booking.service.impl.RoomServiceImpl;

/**
 * Created by Alexey on 29.04.2017.
 */
public class ServiceFactory {

    private static ServiceFactory instance = new ServiceFactory();

    private LoginService loginService = new LoginServiceImpl();
    private RoomService roomService = new RoomServiceImpl();

    private ServiceFactory(){}

    public static ServiceFactory getInstance() {
        return instance;
    }

    public LoginService getLoginService() {
        return loginService;
    }

    public RoomService getRoomService() {
        return roomService;
    }
}
