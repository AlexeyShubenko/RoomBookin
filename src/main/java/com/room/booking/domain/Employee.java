package com.room.booking.domain;

import com.room.booking.dto.EmployeeDto;

/**
 * Created by Alexey on 19.07.2017.
 */
public class Employee {

    private Long id;
    private String name;
    private Role role;
    private String login;
    private String password;

    public Employee() {
    }

    public Employee(Long id, String name, Role role, String login, String password) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.login = login;
        this.password = password;
    }

    public static class Builder{

        Employee employee = new Employee();

        public Builder setName(EmployeeDto employeeDto){
            employee.setName(employeeDto.getName());
            return this;
        }

        public Builder setLogin(EmployeeDto employeeDto){
            employee.setLogin(employeeDto.getLogin());
            return this;
        }

        public Builder setPassword(EmployeeDto employeeDto){
            employee.setPassword(employeeDto.getPassword());
            return this;
        }

        public Builder setRole(EmployeeDto employeeDto){
            employee.setRole(employeeDto.getRole());
            return this;
        }

        public Employee build(){
            return employee;
        }

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", role=" + role +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
