package com.room.booking.service;

import com.room.booking.service.impl.LoginServiceImpl;

/**
 * Created by Alexey on 29.04.2017.
 */
public class ServiceFactory {

    private static ServiceFactory instance = new ServiceFactory();
    private LoginService loginService = new LoginServiceImpl();

    private ServiceFactory(){}

    public static ServiceFactory getInstance() {
        return instance;
    }

    public LoginService getLoginService() {
        return loginService;
    }

}
