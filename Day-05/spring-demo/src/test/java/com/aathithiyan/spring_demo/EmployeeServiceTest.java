package com.aathithiyan.spring_demo.service;

import com.aathithiyan.spring_demo.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.aathithiyan.spring_demo.model.Employee;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import com.aathithiyan.spring_demo.dto.EmployeeResponseDTO;
import com.aathithiyan.spring_demo.model.Department;
import com.aathithiyan.spring_demo.exception.EmployeeNotFoundException;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void testGetEmployeeById() {

        Department department = new Department();
        department.setId(1);
        department.setName("IT");

        Employee employee = new Employee(
                1,
                "Aathithiyan",
                "aathi@gmail.com",
                department,
                45000
        );

        when(employeeRepository.findById(1))
                .thenReturn(Optional.of(employee));

        EmployeeResponseDTO result =
                employeeService.getEmployeeById(1);

        assertNotNull(result);
        assertEquals("Aathithiyan", result.getName());
        assertEquals("aathi@gmail.com", result.getEmail());
        assertEquals(45000, result.getSalary());
    }

    @Test
    void testGetEmployeeByIdNotFound() {

        when(employeeRepository.findById(999))
                .thenReturn(Optional.empty());

        assertThrows(
                EmployeeNotFoundException.class,
                () -> employeeService.getEmployeeById(999)
        );
    }}