package com.room.booking.dao;

import com.room.booking.domain.Employee;

/**
 * Created by Alexey on 19.07.2017.
 */
public interface EmployeeDao {

    Employee getEmployeeByLogin(String login);
    void saveEmployee(Employee employee);

}
