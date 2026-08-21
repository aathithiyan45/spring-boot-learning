package com.aathithiyan.spring_demo.service;

import com.aathithiyan.spring_demo.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    private final List<Employee> employees = new ArrayList<>();

    private int nextId = 1;

    // Create Employee
    public Employee createEmployee(Employee employee) {
        employee.setId(nextId++);
        employees.add(employee);
        return employee;
    }

    // Get All Employees
    public List<Employee> getAllEmployees() {
        return employees;
    }

    // Get Employee By ID
    public Employee getEmployeeById(int id) {

        for (Employee employee : employees) {
            if (employee.getId() == id) {
                return employee;
            }
        }

        return null;
    }

    // Update Employee
    public Employee updateEmployee(int id, Employee updatedEmployee) {

        Employee existingEmployee = getEmployeeById(id);

        if (existingEmployee == null) {
            return null;
        }

        existingEmployee.setName(updatedEmployee.getName());
        existingEmployee.setEmail(updatedEmployee.getEmail());
        existingEmployee.setDepartment(updatedEmployee.getDepartment());
        existingEmployee.setSalary(updatedEmployee.getSalary());

        return existingEmployee;
    }

    // Delete Employee
    public boolean deleteEmployee(int id) {

        Employee employee = getEmployeeById(id);

        if (employee == null) {
            return false;
        }

        employees.remove(employee);
        return true;
    }
}