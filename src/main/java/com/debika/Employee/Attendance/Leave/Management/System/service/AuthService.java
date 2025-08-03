package com.debika.Employee.Attendance.Leave.Management.System.service;

import com.debika.Employee.Attendance.Leave.Management.System.dto.JwtResponseDTO;
import com.debika.Employee.Attendance.Leave.Management.System.dto.LoginRequestDTO;
import com.debika.Employee.Attendance.Leave.Management.System.dto.SignupDTO;
import com.debika.Employee.Attendance.Leave.Management.System.model.Employee;
import com.debika.Employee.Attendance.Leave.Management.System.model.Role;
import com.debika.Employee.Attendance.Leave.Management.System.repository.EmployeeRepository;
import com.debika.Employee.Attendance.Leave.Management.System.util.JwtUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    private EmployeeRepository employeeRepo;

    private PasswordEncoder passwordEncoder;

    private JwtUtils jwtUtils;

    public AuthService(EmployeeRepository employeeRepo, PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
        this.employeeRepo = employeeRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    public ResponseEntity<?> registerUser(SignupDTO request) {
        if (employeeRepo.existsByUsername(request.getUsername())) {
            return ResponseEntity.badRequest().body("Username already exists");
        }

        Employee emp = new Employee();
        emp.setUsername(request.getUsername());
        emp.setEmail(request.getEmail());
        emp.setPassword(passwordEncoder.encode(request.getPassword()));

        try {
            emp.setRole(Role.valueOf(request.getRole().toUpperCase()));
        } catch (Exception e) {
            emp.setRole(Role.EMPLOYEE);
        }

        employeeRepo.save(emp);

        return ResponseEntity.ok("User registered successfully");
    }

    public JwtResponseDTO authenticateUser(LoginRequestDTO request) {
        try {
            Employee emp = employeeRepo.findByEmail(request.getEmail())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            if (!passwordEncoder.matches(request.getPassword(), emp.getPassword())) {
                throw new EntityNotFoundException("Invalid credentials");
            }

            Map<String, Object> claims = new HashMap<>();
            claims.put("username", emp.getEmail());
            claims.put("role", emp.getRole()); // more useful than password

            String token = jwtUtils.createToken(claims, emp.getEmail());
            return new JwtResponseDTO(token, emp.getEmail());

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Authentication failed: " + e.getMessage());
        }
    }


}
