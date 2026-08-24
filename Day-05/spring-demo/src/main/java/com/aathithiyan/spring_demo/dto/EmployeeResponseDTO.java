package com.aathithiyan.spring_demo.dto;

public class EmployeeResponseDTO {

    private Integer id;
    private String name;
    private String email;
    private Double salary;
    private String department;

    public EmployeeResponseDTO() {
    }

    public EmployeeResponseDTO(
            Integer id,
            String name,
            String email,
            Double salary,
            String department) {

        this.id = id;
        this.name = name;
        this.email = email;
        this.salary = salary;
        this.department = department;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}