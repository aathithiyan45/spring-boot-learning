package com.aathithiyan.spring_demo.controller;

import com.aathithiyan.spring_demo.model.Employee;
import com.aathithiyan.spring_demo.service.EmployeeService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Employee> createEmployee(
            @RequestBody Employee employee) {

        Employee newEmployee =
                employeeService.createEmployee(employee);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(newEmployee);
    }

    // GET ALL
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(
            @PathVariable int id) {

        Employee employee =
                employeeService.getEmployeeById(id);

        if (employee == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(employee);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable int id,
            @RequestBody Employee employee) {

        Employee updatedEmployee =
                employeeService.updateEmployee(id, employee);

        if (updatedEmployee == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedEmployee);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(
            @PathVariable int id) {

        boolean deleted =
                employeeService.deleteEmployee(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    // FIND BY EMAIL
    @GetMapping("/email/{email}")
    public ResponseEntity<Employee> getEmployeeByEmail(
            @PathVariable String email) {

        Employee employee =
                employeeService.getEmployeeByEmail(email);

        if (employee == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(employee);
    }

    // FIND BY DEPARTMENT ID
    @GetMapping("/department/{departmentId}")
    public List<Employee> getEmployeesByDepartment(
            @PathVariable Integer departmentId) {

        return employeeService
                .getEmployeesByDepartmentId(departmentId);
    }

    // DERIVED QUERY
    @GetMapping("/salary/greater-than/{salary}")
    public List<Employee> getEmployeesBySalaryGreaterThan(
            @PathVariable double salary) {

        return employeeService
                .getEmployeesBySalaryGreaterThan(salary);
    }

    // CUSTOM JPQL QUERY
    @GetMapping("/salary/high/{salary}")
    public List<Employee> getHighSalaryEmployees(
            @PathVariable double salary) {

        return employeeService
                .getHighSalaryEmployees(salary);
    }

    @GetMapping("/salary/native/{salary}")
    public List<Employee> getHighSalaryNative(
            @PathVariable double salary) {

        return employeeService.getHighSalaryNative(salary);
    }

    @GetMapping("/page")
    public Page<Employee> getEmployees(Pageable pageable) {
        return employeeService.getAllEmployees(pageable);
    }
}