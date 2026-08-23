package com.aathithiyan.spring_demo.repository;

import com.aathithiyan.spring_demo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Optional<Employee> findByEmail(String email);

    List<Employee> findByDepartment(String department);

    List<Employee> findBySalaryGreaterThan(double salary);
}