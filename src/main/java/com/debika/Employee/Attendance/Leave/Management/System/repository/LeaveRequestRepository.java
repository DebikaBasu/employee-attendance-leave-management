package com.debika.Employee.Attendance.Leave.Management.System.repository;

import com.debika.Employee.Attendance.Leave.Management.System.model.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
    List<LeaveRequest> findByEmployeeId(Long employeeId);
}
