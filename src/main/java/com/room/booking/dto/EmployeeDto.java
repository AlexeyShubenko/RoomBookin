package com.room.booking.dto;

import com.room.booking.domain.Employee;
import com.room.booking.domain.Role;

/**
 * Created by Alexey on 19.07.2017.
 */
public class EmployeeDto {

    private Long id;
    private String name;
    private Role role;
    private String login;
    private String password;

    public EmployeeDto() {
    }

    public EmployeeDto(Long id, String name, Role role, String login, String password) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.login = login;
        this.password = password;
    }

    public static class Builder{

        EmployeeDto employeeDto = new EmployeeDto();

        public Builder setName(Employee employee){
            employeeDto.setName(employee.getName());
            return this;
        }

        public Builder setLogin(Employee employee){
            employeeDto.setLogin(employee.getLogin());
            return this;
        }

        public Builder setPassword(Employee employee){
            employeeDto.setPassword(employee.getPassword());
            return this;
        }

        public Builder setRole(Employee employee){
            employeeDto.setRole(employee.getRole());
            return this;
        }

        public EmployeeDto build(){
            return employeeDto;
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

}
