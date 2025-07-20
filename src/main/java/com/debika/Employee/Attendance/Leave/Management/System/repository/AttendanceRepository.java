package com.debika.Employee.Attendance.Leave.Management.System.repository;

import com.debika.Employee.Attendance.Leave.Management.System.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByEmployeeId(Long employeeId);
}
