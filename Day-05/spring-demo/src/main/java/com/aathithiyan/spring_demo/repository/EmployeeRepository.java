package com.aathithiyan.spring_demo.repository;

import com.aathithiyan.spring_demo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}