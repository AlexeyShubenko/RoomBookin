package com.room.booking.dao.impl;

import com.room.booking.dao.EmployeeDao;
import com.room.booking.domain.Employee;
import com.room.booking.domain.Role;
import com.room.booking.exceptions.DaoException;
import com.room.booking.service.utils.ConnectionProxy;
import com.room.booking.service.utils.EntityManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Alexey on 19.07.2017.
 */
public class EmployeeDaoImpl implements EmployeeDao {

    private static EmployeeDaoImpl instance = new EmployeeDaoImpl();
    private String preparedStatementQuery = "select * from employee where login=?";


    private EmployeeDaoImpl(){}

    public static EmployeeDaoImpl getInstance() {
        return instance;
    }

    @Override
    public Employee getEmployeeByLogin(String login) {
        Employee employee = null;
        Role role;
        try(ConnectionProxy connectionProxy = EntityManager.getEntityManager().getConnection()){
            PreparedStatement preparedStatement = connectionProxy.createPreparedStatement(preparedStatementQuery);
            preparedStatement.setString(1,login);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                employee = new Employee();
                employee.setName(resultSet.getString("name"));
                employee.setLogin(resultSet.getString("login"));
                employee.setPassword(resultSet.getString("password"));
                if(Integer.parseInt(resultSet.getString("role_id"))==1){
                    role = Role.ADMIN;
                }else
                    role = Role.USER;
                employee.setRole(role);
            }
        }catch (SQLException ex){
            throw new DaoException(ex);
        }
        return employee;
    }
}
