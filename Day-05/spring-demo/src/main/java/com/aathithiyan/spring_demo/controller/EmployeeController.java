package com.aathithiyan.spring_demo.controller;

import com.aathithiyan.spring_demo.dto.EmployeeRequestDTO;
import com.aathithiyan.spring_demo.dto.EmployeeResponseDTO;
import com.aathithiyan.spring_demo.service.EmployeeService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // =========================
    // CREATE
    // =========================

    @PostMapping
    public ResponseEntity<EmployeeResponseDTO> createEmployee(
            @Valid @RequestBody EmployeeRequestDTO dto) {

        EmployeeResponseDTO response =
                employeeService.createEmployee(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // =========================
    // GET ALL
    // =========================

    @GetMapping
    public List<EmployeeResponseDTO> getAllEmployees() {

        return employeeService.getAllEmployees();
    }

    // =========================
    // GET BY ID
    // =========================

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> getEmployeeById(
            @PathVariable int id) {

        EmployeeResponseDTO employee =
                employeeService.getEmployeeById(id);

        if (employee == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(employee);
    }

    // =========================
    // UPDATE
    // =========================

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(
            @PathVariable int id,
            @Valid @RequestBody EmployeeRequestDTO dto) {

        EmployeeResponseDTO updatedEmployee =
                employeeService.updateEmployee(id, dto);

        if (updatedEmployee == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedEmployee);
    }

    // =========================
    // DELETE
    // =========================

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

    // =========================
    // FIND BY EMAIL
    // =========================

    @GetMapping("/email/{email}")
    public ResponseEntity<EmployeeResponseDTO> getEmployeeByEmail(
            @PathVariable String email) {

        EmployeeResponseDTO employee =
                employeeService.getEmployeeByEmail(email);

        if (employee == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(employee);
    }

    // =========================
    // FIND BY DEPARTMENT
    // =========================

    @GetMapping("/department/{departmentId}")
    public List<EmployeeResponseDTO> getEmployeesByDepartment(
            @PathVariable Integer departmentId) {

        return employeeService
                .getEmployeesByDepartmentId(departmentId);
    }

    // =========================
    // DERIVED QUERY
    // =========================

    @GetMapping("/salary/greater-than/{salary}")
    public List<EmployeeResponseDTO> getEmployeesBySalaryGreaterThan(
            @PathVariable double salary) {

        return employeeService
                .getEmployeesBySalaryGreaterThan(salary);
    }

    // =========================
    // JPQL
    // =========================

    @GetMapping("/salary/high/{salary}")
    public List<EmployeeResponseDTO> getHighSalaryEmployees(
            @PathVariable double salary) {

        return employeeService
                .getHighSalaryEmployees(salary);
    }

    // =========================
    // NATIVE QUERY
    // =========================

    @GetMapping("/salary/native/{salary}")
    public List<EmployeeResponseDTO> getHighSalaryNative(
            @PathVariable double salary) {

        return employeeService
                .getHighSalaryNative(salary);
    }

    // =========================
    // PAGINATION + SORTING
    // =========================

    @GetMapping("/page")
    public Page<EmployeeResponseDTO> getEmployees(
            Pageable pageable) {

        return employeeService
                .getAllEmployees(pageable);
    }

    // =========================
    // TRANSACTION TEST
    // =========================

    @GetMapping("/transaction-test")
    public ResponseEntity<String> transactionTest() {

        try {

            employeeService.createEmployeeWithRollbackTest();

            return ResponseEntity.ok(
                    "Transaction completed successfully"
            );

        } catch (RuntimeException e) {

            return ResponseEntity.ok(
                    "Transaction failed and rolled back"
            );
        }
    }

    // =========================
    // SEARCH BY NAME
    // =========================

    @GetMapping("/search")
    public List<EmployeeResponseDTO> searchEmployees(
            @RequestParam String name) {

        return employeeService
                .searchEmployeesByName(name);
    }

    // =========================
    // SALARY RANGE
    // =========================

    @GetMapping("/filter/salary")
    public List<EmployeeResponseDTO> getEmployeesBySalaryRange(
            @RequestParam double min,
            @RequestParam double max) {

        return employeeService
                .getEmployeesBySalaryRange(min, max);
    }

    // =========================
    // DYNAMIC FILTER
    // =========================

    @GetMapping("/filter")
    public List<EmployeeResponseDTO> filterEmployees(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer departmentId,
            @RequestParam(required = false) Double minSalary,
            @RequestParam(required = false) Double maxSalary) {

        return employeeService.filterEmployees(
                name,
                departmentId,
                minSalary,
                maxSalary
        );
    }
}