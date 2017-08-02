package com.room.booking.service;

import com.room.booking.service.impl.EmployeeServiceImpl;
import com.room.booking.service.impl.LoginServiceImpl;
import com.room.booking.service.impl.RoomBookingServiceImpl;
import com.room.booking.service.impl.RoomServiceImpl;

/**
 * Created by Alexey on 29.04.2017.
 */
public class ServiceFactory {

    private static ServiceFactory instance = new ServiceFactory();

    private LoginService loginService = new LoginServiceImpl();
    private RoomService roomService = new RoomServiceImpl();
    private EmployeeService employeeService = new EmployeeServiceImpl();
    private RoomBookingService roomBookingService = new RoomBookingServiceImpl();

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

    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    public RoomBookingService getRoomBookingService() {
        return roomBookingService;
    }
}
