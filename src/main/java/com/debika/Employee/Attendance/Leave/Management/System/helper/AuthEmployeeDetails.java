package com.debika.Employee.Attendance.Leave.Management.System.helper;

import com.debika.Employee.Attendance.Leave.Management.System.model.Employee;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class AuthEmployeeDetails extends Employee implements UserDetails {

    private String username;
    private String password;

    public AuthEmployeeDetails(Employee employee) {
        this.username = employee.getEmail();
        this.password = employee.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }
}