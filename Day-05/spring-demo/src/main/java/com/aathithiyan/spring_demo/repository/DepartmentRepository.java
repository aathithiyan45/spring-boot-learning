package com.aathithiyan.spring_demo.repository;

import com.aathithiyan.spring_demo.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository
        extends JpaRepository<Department, Integer> {
}