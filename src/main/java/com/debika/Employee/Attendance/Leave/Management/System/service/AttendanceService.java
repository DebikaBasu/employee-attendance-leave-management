package com.debika.Employee.Attendance.Leave.Management.System.service;

import com.debika.Employee.Attendance.Leave.Management.System.dto.AttendanceDTO;
import com.debika.Employee.Attendance.Leave.Management.System.model.Attendance;
import com.debika.Employee.Attendance.Leave.Management.System.model.Employee;
import com.debika.Employee.Attendance.Leave.Management.System.repository.AttendanceRepository;
import com.debika.Employee.Attendance.Leave.Management.System.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepo;

    @Autowired
    private EmployeeRepository employeeRepo;

    public String markAttendance(AttendanceDTO dto) {
        Employee emp = employeeRepo.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Attendance attendance = Attendance.builder()
                .date(dto.getDate())
                .checkInTime(dto.getCheckInTime().toLocalTime())
                .checkOutTime(dto.getCheckOutTime().toLocalTime())
                .status(dto.getStatus())
                .employee(emp)
                .build();
        attendanceRepo.save(attendance);
        return "Attendance marked successfully";
    }

    public List<AttendanceDTO> getAllAttendance() {
        return attendanceRepo.findAll().stream().map(att -> {
            AttendanceDTO dto = new AttendanceDTO();
            dto.setId(att.getId());
            dto.setEmployeeId(att.getEmployee().getId());
            dto.setEmployeeName(att.getEmployee().getUsername());
            dto.setDate(att.getDate());
            dto.setCheckInTime(att.getCheckInTime() != null ? att.getDate().atTime(att.getCheckInTime()) : null);
            dto.setCheckOutTime(att.getCheckOutTime() != null ? att.getDate().atTime(att.getCheckOutTime()) : null);
            dto.setStatus(att.getStatus());
            return dto;
        }).collect(Collectors.toList());
    }


    public List<AttendanceDTO> getAttendanceByEmployeeId(Long empId) {
        return attendanceRepo.findByEmployeeId(empId).stream().map(att -> {
            AttendanceDTO dto = new AttendanceDTO();
            dto.setId(att.getId());
            dto.setEmployeeId(att.getEmployee().getId());
            dto.setEmployeeName(att.getEmployee().getUsername());
            dto.setDate(att.getDate());
            dto.setCheckInTime(att.getCheckInTime() != null ? att.getDate().atTime(att.getCheckInTime()) : null);
            dto.setCheckOutTime(att.getCheckOutTime() != null ? att.getDate().atTime(att.getCheckOutTime()) : null);
            dto.setStatus(att.getStatus());
            return dto;
        }).collect(Collectors.toList());
    }
}
