package com.debika.Employee.Attendance.Leave.Management.System.repository;

import com.debika.Employee.Attendance.Leave.Management.System.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmail(String email);
    Optional<Employee> findByUsername(String username);
    boolean existsByUsername(String username);

}
