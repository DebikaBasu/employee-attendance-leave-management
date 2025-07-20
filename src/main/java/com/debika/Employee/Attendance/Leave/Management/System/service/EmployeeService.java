package com.debika.Employee.Attendance.Leave.Management.System.service;

import com.debika.Employee.Attendance.Leave.Management.System.dto.EmployeeDTO;
import com.debika.Employee.Attendance.Leave.Management.System.model.Employee;
import com.debika.Employee.Attendance.Leave.Management.System.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public EmployeeDTO getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    private EmployeeDTO convertToDTO(Employee employee) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(employee.getId());
        dto.setUsername(employee.getUsername());
        dto.setEmail(employee.getEmail());
        dto.setRole(employee.getRole().name());
        return dto;
    }
}
