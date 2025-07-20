package com.debika.Employee.Attendance.Leave.Management.System.controller;

import com.debika.Employee.Attendance.Leave.Management.System.dto.LeaveRequestDTO;
import com.debika.Employee.Attendance.Leave.Management.System.service.LeaveRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leave")
public class LeaveRequestController {

    @Autowired
    private LeaveRequestService leaveService;

    @PostMapping("/apply")
    public String applyLeave(@RequestBody LeaveRequestDTO dto) {
        return leaveService.applyLeave(dto);
    }

    @GetMapping("/all")
    public List<LeaveRequestDTO> getAllLeaves() {
        return leaveService.getAllLeaveRequests();
    }

    @GetMapping("/employee/{id}")
    public List<LeaveRequestDTO> getLeavesByEmployee(@PathVariable Long id) {
        return leaveService.getLeavesByEmployee(id);
    }

    @PutMapping("/update/{id}")
    public String updateLeaveStatus(@PathVariable Long id, @RequestParam String status) {
        return leaveService.updateLeaveStatus(id, status);
    }
}
