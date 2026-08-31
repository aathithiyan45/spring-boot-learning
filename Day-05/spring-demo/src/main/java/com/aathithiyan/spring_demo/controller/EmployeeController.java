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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;



@Tag(
        name = "Employee Management",
        description = "APIs for creating, retrieving, updating and deleting employees"
)
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

    @Operation(
            summary = "Create employee",
            description = "Creates a new employee"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Employee created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid employee data")
    })
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

    @Operation(
            summary = "Get all employees",
            description = "Returns all employees"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Employees retrieved successfully"
    )
    @GetMapping
    public List<EmployeeResponseDTO> getAllEmployees() {

        return employeeService.getAllEmployees();
    }

    // =========================
    // GET BY ID
    // =========================

    @Operation(
            summary = "Get employee by ID",
            description = "Returns an employee using their unique ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Employee found"),
            @ApiResponse(responseCode = "404", description = "Employee not found")
    })
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

    @Operation(
            summary = "Update employee",
            description = "Updates an existing employee using their ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Employee updated successfully"),
            @ApiResponse(responseCode = "404", description = "Employee not found"),
            @ApiResponse(responseCode = "400", description = "Invalid employee data")
    })
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

    @Operation(
            summary = "Delete employee",
            description = "Deletes an employee using their ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Employee deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Employee not found")
    })
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

    @Operation(
            summary = "Find employees by salary",
            description = "Returns employees whose salary is greater than the specified amount using a derived query"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Employees retrieved successfully"
    )
    @GetMapping("/salary/greater-than/{salary}")
    public List<EmployeeResponseDTO> getEmployeesBySalaryGreaterThan(
            @PathVariable double salary) {

        return employeeService
                .getEmployeesBySalaryGreaterThan(salary);
    }

    // =========================
    // JPQL
    // =========================

    @Operation(
            summary = "Find high salary employees using JPQL",
            description = "Returns employees whose salary is greater than the specified amount using JPQL"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Employees retrieved successfully"
    )
    @GetMapping("/salary/high/{salary}")
    public List<EmployeeResponseDTO> getHighSalaryEmployees(
            @PathVariable double salary) {

        return employeeService
                .getHighSalaryEmployees(salary);
    }

    // =========================
    // NATIVE QUERY
    // =========================

    @Operation(
            summary = "Find high salary employees using native SQL",
            description = "Returns employees whose salary is greater than the specified amount using a native SQL query"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Employees retrieved successfully"
    )
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