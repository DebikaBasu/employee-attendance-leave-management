package com.debika.Employee.Attendance.Leave.Management.System.controller;

import com.debika.Employee.Attendance.Leave.Management.System.dto.JwtResponseDTO;
import com.debika.Employee.Attendance.Leave.Management.System.dto.LoginRequestDTO;
import com.debika.Employee.Attendance.Leave.Management.System.dto.SignupDTO;
import com.debika.Employee.Attendance.Leave.Management.System.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO request) {
        try {
            JwtResponseDTO response = authService.authenticateUser(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupDTO request) {
        return authService.registerUser(request);
    }
}

