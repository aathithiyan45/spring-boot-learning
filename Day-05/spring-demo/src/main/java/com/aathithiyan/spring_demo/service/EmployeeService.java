package com.aathithiyan.spring_demo.service;

import com.aathithiyan.spring_demo.dto.EmployeeRequestDTO;
import com.aathithiyan.spring_demo.dto.EmployeeResponseDTO;
import com.aathithiyan.spring_demo.model.Department;
import com.aathithiyan.spring_demo.model.Employee;
import com.aathithiyan.spring_demo.repository.DepartmentRepository;
import com.aathithiyan.spring_demo.repository.EmployeeRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import com.aathithiyan.spring_demo.exception.EmployeeNotFoundException;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeService(
            EmployeeRepository employeeRepository,
            DepartmentRepository departmentRepository) {

        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    // =========================
    // ENTITY → RESPONSE DTO
    // =========================

    private EmployeeResponseDTO convertToResponseDTO(
            Employee employee) {

        return new EmployeeResponseDTO(
                employee.getId(),
                employee.getName(),
                employee.getEmail(),
                employee.getSalary(),
                employee.getDepartment().getName()
        );
    }

    // =========================
    // DTO → ENTITY
    // =========================

    private Employee convertToEntity(
            EmployeeRequestDTO dto) {

        Department department =
                departmentRepository.findById(
                        dto.getDepartmentId()
                ).orElseThrow(() ->
                        new RuntimeException(
                                "Department not found"
                        ));

        Employee employee = new Employee();

        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        employee.setSalary(dto.getSalary());
        employee.setDepartment(department);

        return employee;
    }

    // =========================
    // CREATE
    // DTO → Entity → DB
    // Entity → Response DTO
    // =========================

    public EmployeeResponseDTO createEmployee(
            EmployeeRequestDTO dto) {

        Employee employee = convertToEntity(dto);

        Employee savedEmployee =
                employeeRepository.save(employee);

        return convertToResponseDTO(savedEmployee);
    }

    // =========================
    // GET ALL
    // Entity → DTO
    // =========================

    public List<EmployeeResponseDTO> getAllEmployees() {

        return employeeRepository.findAll()
                .stream()
                .map(this::convertToResponseDTO)
                .toList();
    }

    // =========================
    // GET BY ID
    // Entity → DTO
    // =========================

    public EmployeeResponseDTO getEmployeeById(int id) {

        Employee employee =
                employeeRepository.findById(id)
                        .orElseThrow(() ->
                                new EmployeeNotFoundException(
                                        "Employee not found with id: " + id
                                ));

        return convertToResponseDTO(employee);
    }

    // =========================
    // UPDATE
    // DTO → Entity
    // =========================

    public EmployeeResponseDTO updateEmployee(
            int id,
            EmployeeRequestDTO dto) {

        Employee existingEmployee =
                employeeRepository.findById(id)
                        .orElse(null);

        if (existingEmployee == null) {
            return null;
        }

        Department department =
                departmentRepository.findById(
                        dto.getDepartmentId()
                ).orElseThrow(() ->
                        new RuntimeException(
                                "Department not found"
                        ));

        existingEmployee.setName(dto.getName());
        existingEmployee.setEmail(dto.getEmail());
        existingEmployee.setSalary(dto.getSalary());
        existingEmployee.setDepartment(department);

        Employee updatedEmployee =
                employeeRepository.save(existingEmployee);

        return convertToResponseDTO(updatedEmployee);
    }

    // =========================
    // DELETE
    // =========================

    public boolean deleteEmployee(int id) {

        if (!employeeRepository.existsById(id)) {
            return false;
        }

        employeeRepository.deleteById(id);

        return true;
    }

    // =========================
    // FIND BY EMAIL
    // Entity → DTO
    // =========================

    public EmployeeResponseDTO getEmployeeByEmail(
            String email) {

        Employee employee =
                employeeRepository.findByEmail(email)
                        .orElse(null);

        if (employee == null) {
            return null;
        }

        return convertToResponseDTO(employee);
    }

    // =========================
    // FIND BY DEPARTMENT
    // Entity → DTO
    // =========================

    public List<EmployeeResponseDTO>
    getEmployeesByDepartmentId(Integer departmentId) {

        return employeeRepository
                .findByDepartmentId(departmentId)
                .stream()
                .map(this::convertToResponseDTO)
                .toList();
    }

    // =========================
    // DERIVED QUERY
    // Entity → DTO
    // =========================

    public List<EmployeeResponseDTO>
    getEmployeesBySalaryGreaterThan(double salary) {

        return employeeRepository
                .findBySalaryGreaterThan(salary)
                .stream()
                .map(this::convertToResponseDTO)
                .toList();
    }

    // =========================
    // JPQL
    // Entity → DTO
    // =========================

    public List<EmployeeResponseDTO>
    getHighSalaryEmployees(double salary) {

        return employeeRepository
                .findHighSalaryEmployees(salary)
                .stream()
                .map(this::convertToResponseDTO)
                .toList();
    }

    // =========================
    // NATIVE QUERY
    // Entity → DTO
    // =========================

    public List<EmployeeResponseDTO>
    getHighSalaryNative(double salary) {

        return employeeRepository
                .findHighSalaryNative(salary)
                .stream()
                .map(this::convertToResponseDTO)
                .toList();
    }

    // =========================
    // PAGINATION + SORTING
    // Entity → DTO
    // =========================

    public Page<EmployeeResponseDTO>
    getAllEmployees(Pageable pageable) {

        return employeeRepository
                .findAll(pageable)
                .map(this::convertToResponseDTO);
    }

    @Transactional
    public void createEmployeeWithRollbackTest() {

        Department department =
                departmentRepository.findById(1)
                        .orElseThrow(() ->
                                new RuntimeException("Department not found"));

        Employee employee = new Employee();

        employee.setName("Rollback Employee");
        employee.setEmail("rollback@test.com");
        employee.setSalary(60000);
        employee.setDepartment(department);

        employeeRepository.save(employee);

        // Intentional failure
        throw new RuntimeException("Intentional failure - testing rollback");
    }
}