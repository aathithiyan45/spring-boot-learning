package com.aathithiyan.spring_demo.service;

import com.aathithiyan.spring_demo.model.Employee;
import com.aathithiyan.spring_demo.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // CREATE
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // GET ALL
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // GET BY ID
    public Employee getEmployeeById(int id) {
        return employeeRepository.findById(id).orElse(null);
    }

    // UPDATE
    public Employee updateEmployee(int id, Employee updatedEmployee) {

        Employee existingEmployee = getEmployeeById(id);

        if (existingEmployee == null) {
            return null;
        }

        existingEmployee.setName(updatedEmployee.getName());
        existingEmployee.setEmail(updatedEmployee.getEmail());
        existingEmployee.setDepartment(updatedEmployee.getDepartment());
        existingEmployee.setSalary(updatedEmployee.getSalary());

        return employeeRepository.save(existingEmployee);
    }

    // DELETE
    public boolean deleteEmployee(int id) {

        if (!employeeRepository.existsById(id)) {
            return false;
        }

        employeeRepository.deleteById(id);
        return true;
    }

    // FIND BY EMAIL
    public Employee getEmployeeByEmail(String email) {
        return employeeRepository.findByEmail(email).orElse(null);
    }

    // FIND BY DEPARTMENT ID
    public List<Employee> getEmployeesByDepartmentId(Integer departmentId) {
        return employeeRepository.findByDepartmentId(departmentId);
    }

    // FIND BY SALARY
    public List<Employee> getEmployeesBySalaryGreaterThan(double salary) {
        return employeeRepository.findBySalaryGreaterThan(salary);
    }

    // CUSTOM JPQL QUERY
    public List<Employee> getHighSalaryEmployees(double salary) {
        return employeeRepository.findHighSalaryEmployees(salary);
    }

    public List<Employee> getHighSalaryNative(double salary) {
        return employeeRepository.findHighSalaryNative(salary);
    }

    public Page<Employee> getAllEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }
}