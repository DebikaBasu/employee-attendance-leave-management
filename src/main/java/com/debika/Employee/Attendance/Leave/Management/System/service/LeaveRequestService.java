package com.debika.Employee.Attendance.Leave.Management.System.service;

import com.debika.Employee.Attendance.Leave.Management.System.dto.LeaveRequestDTO;
import com.debika.Employee.Attendance.Leave.Management.System.model.Employee;
import com.debika.Employee.Attendance.Leave.Management.System.model.LeaveRequest;
import com.debika.Employee.Attendance.Leave.Management.System.repository.EmployeeRepository;
import com.debika.Employee.Attendance.Leave.Management.System.repository.LeaveRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaveRequestService {

    @Autowired
    private LeaveRequestRepository leaveRequestRepo;

    @Autowired
    private EmployeeRepository employeeRepo;

    public String applyLeave(LeaveRequestDTO dto) {
        Employee emp = employeeRepo.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        LeaveRequest leave = LeaveRequest.builder()
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .reason(dto.getReason())
                .status("Pending")
                .employee(emp)
                .build();

        leaveRequestRepo.save(leave);
        return "Leave request submitted";
    }

    public List<LeaveRequestDTO> getAllLeaveRequests() {
        return leaveRequestRepo.findAll().stream().map(leave -> {
            LeaveRequestDTO dto = new LeaveRequestDTO();
            dto.setId(leave.getId());
            dto.setEmployeeId(leave.getEmployee().getId());
            dto.setEmployeeName(leave.getEmployee().getUsername());
            dto.setStartDate(leave.getStartDate());
            dto.setEndDate(leave.getEndDate());
            dto.setReason(leave.getReason());
            dto.setStatus(leave.getStatus());
            return dto;
        }).collect(Collectors.toList());
    }

    public List<LeaveRequestDTO> getLeavesByEmployee(Long empId) {
        return leaveRequestRepo.findByEmployeeId(empId).stream().map(leave -> {
            LeaveRequestDTO dto = new LeaveRequestDTO();
            dto.setId(leave.getId());
            dto.setEmployeeId(leave.getEmployee().getId());
            dto.setEmployeeName(leave.getEmployee().getUsername());
            dto.setStartDate(leave.getStartDate());
            dto.setEndDate(leave.getEndDate());
            dto.setReason(leave.getReason());
            dto.setStatus(leave.getStatus());
            return dto;
        }).collect(Collectors.toList());
    }

    public String updateLeaveStatus(Long leaveId, String status) {
        LeaveRequest leave = leaveRequestRepo.findById(leaveId)
                .orElseThrow(() -> new RuntimeException("Leave request not found"));
        leave.setStatus(status);
        leaveRequestRepo.save(leave);
        return "Leave status updated to: " + status;
    }
}
