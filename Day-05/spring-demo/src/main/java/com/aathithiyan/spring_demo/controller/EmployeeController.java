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
    // Request DTO → Entity
    // Entity → Response DTO
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
    // Entity → Response DTO
    // =========================

    @GetMapping
    public List<EmployeeResponseDTO> getAllEmployees() {

        return employeeService.getAllEmployees();
    }

    // =========================
    // GET BY ID
    // Entity → Response DTO
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
    // Request DTO → Entity
    // Entity → Response DTO
    // =========================

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(
            @PathVariable int id,
            @RequestBody EmployeeRequestDTO dto) {

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
    // Entity → Response DTO
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
    // Entity → Response DTO
    // =========================

    @GetMapping("/department/{departmentId}")
    public List<EmployeeResponseDTO> getEmployeesByDepartment(
            @PathVariable Integer departmentId) {

        return employeeService
                .getEmployeesByDepartmentId(departmentId);
    }

    // =========================
    // DERIVED QUERY
    // Entity → Response DTO
    // =========================

    @GetMapping("/salary/greater-than/{salary}")
    public List<EmployeeResponseDTO> getEmployeesBySalaryGreaterThan(
            @PathVariable double salary) {

        return employeeService
                .getEmployeesBySalaryGreaterThan(salary);
    }

    // =========================
    // JPQL
    // Entity → Response DTO
    // =========================

    @GetMapping("/salary/high/{salary}")
    public List<EmployeeResponseDTO> getHighSalaryEmployees(
            @PathVariable double salary) {

        return employeeService
                .getHighSalaryEmployees(salary);
    }

    // =========================
    // NATIVE QUERY
    // Entity → Response DTO
    // =========================

    @GetMapping("/salary/native/{salary}")
    public List<EmployeeResponseDTO> getHighSalaryNative(
            @PathVariable double salary) {

        return employeeService
                .getHighSalaryNative(salary);
    }

    // =========================
    // PAGINATION + SORTING
    // Entity → Response DTO
    // =========================

    @GetMapping("/page")
    public Page<EmployeeResponseDTO> getEmployees(
            Pageable pageable) {

        return employeeService
                .getAllEmployees(pageable);
    }
}