package com.debika.Employee.Attendance.Leave.Management.System.controller;

import com.debika.Employee.Attendance.Leave.Management.System.dto.EmployeeDTO;
import com.debika.Employee.Attendance.Leave.Management.System.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
//@CrossOrigin("*")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/all")
    public List<EmployeeDTO> getAllEmployees() {
//        System.out.println("Current User: " + authentication.getName());
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public EmployeeDTO getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }
}
