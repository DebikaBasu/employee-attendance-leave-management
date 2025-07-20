package com.debika.Employee.Attendance.Leave.Management.System.dto;

import lombok.Data;

@Data
public class SignupDTO {
    private String username;
    private String email;
    private String password;
    private String role;  // Expects: "ADMIN" or "EMPLOYEE"
}
