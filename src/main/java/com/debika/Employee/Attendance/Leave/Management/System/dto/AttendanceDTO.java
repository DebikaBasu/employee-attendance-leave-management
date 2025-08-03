package com.debika.Employee.Attendance.Leave.Management.System.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class AttendanceDTO {
    private Long id;
    private Long employeeId;
    private String employeeName;
    private LocalDate date;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
    private String status;
}
