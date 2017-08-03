package com.room.booking.service.impl;

import com.room.booking.dao.impl.EmployeeDaoImpl;
import com.room.booking.domain.Employee;
import com.room.booking.service.LoginService;
import java.util.Objects;

/**
 * Created by Alexey on 19.07.2017.
 */
public class LoginServiceImpl implements LoginService {

    @Override
    public Employee verifyEmployee(String login, String password) {
        Employee employee = EmployeeDaoImpl.getInstance().getEmployeeByLogin(login);
        //if employee exists => true
        if(Objects.nonNull(employee) && employee.getPassword().equals(password)){
            return employee;
        }else
            return null;
    }
}
