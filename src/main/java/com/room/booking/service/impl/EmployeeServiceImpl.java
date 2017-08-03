package com.room.booking.service.impl;

import com.room.booking.dao.impl.EmployeeDaoImpl;
import com.room.booking.domain.Employee;
import com.room.booking.domain.Role;
import com.room.booking.exceptions.DaoException;
import com.room.booking.exceptions.DbException;
import com.room.booking.exceptions.EmployeeExistException;
import com.room.booking.service.EmployeeService;
import com.room.booking.service.utils.EntityManager;

import java.util.Objects;

/**
 * Created by Alexey on 20.07.2017.
 */
public class EmployeeServiceImpl implements EmployeeService {

    @Override
    public void saveEmployee(String name, String login, String password) {
        try {
            EntityManager.getEntityManager().beginTransaction();

            Employee employee = EmployeeDaoImpl.getInstance().getEmployeeByLogin(login);
            if (Objects.isNull(employee)) {
                Employee newEmployee = new Employee();
                newEmployee.setName(name);
                newEmployee.setLogin(login);
                newEmployee.setPassword(password);
                newEmployee.setRole(Role.USER);
                EmployeeDaoImpl.getInstance().saveEmployee(newEmployee);

            } else {
                throw new EmployeeExistException("Employee already exist!");
            }
            EntityManager.getEntityManager().commit();
        }catch (DaoException ex){
            EntityManager.getEntityManager().rollback();
            throw new DbException(ex.getMessage());
        }
    }

}
