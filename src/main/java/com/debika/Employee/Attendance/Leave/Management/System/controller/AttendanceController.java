package com.debika.Employee.Attendance.Leave.Management.System.controller;

import com.debika.Employee.Attendance.Leave.Management.System.dto.AttendanceDTO;
import com.debika.Employee.Attendance.Leave.Management.System.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/mark")
    public String markAttendance(@RequestBody AttendanceDTO dto) {
        return attendanceService.markAttendance(dto);
    }

    @GetMapping("/all")
    public List<AttendanceDTO> getAllAttendance() {
        return attendanceService.getAllAttendance();
    }

    @GetMapping("/employee/{id}")
    public List<AttendanceDTO> getByEmployee(@PathVariable Long id) {
        return attendanceService.getAttendanceByEmployeeId(id);
    }
}
