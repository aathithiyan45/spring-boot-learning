package com.aathithiyan.spring_demo.repository;

import com.aathithiyan.spring_demo.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository
        extends JpaRepository<Employee, Integer>,
        JpaSpecificationExecutor<Employee> {

    Optional<Employee> findByEmail(String email);

    List<Employee> findBySalaryGreaterThan(double salary);

    List<Employee> findByDepartmentId(Integer departmentId);

    @Query("SELECT e FROM Employee e WHERE e.salary > :salary")
    List<Employee> findHighSalaryEmployees(
            @Param("salary") double salary
    );

    @Query(
            value = "SELECT * FROM employee WHERE salary > :salary",
            nativeQuery = true
    )
    List<Employee> findHighSalaryNative(
            @Param("salary") double salary
    );

    Page<Employee> findAll(Pageable pageable);

    List<Employee> findByNameContainingIgnoreCase(String name);

    List<Employee> findBySalaryBetween(
            double minSalary,
            double maxSalary
    );
}