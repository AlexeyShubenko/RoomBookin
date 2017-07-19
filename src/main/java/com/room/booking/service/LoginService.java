package com.room.booking.service;

import com.room.booking.domain.Employee;

/**
 * Created by Alexey on 19.07.2017.
 */
public interface LoginService {

    Employee verifyEmployee(String login, String password);

}
