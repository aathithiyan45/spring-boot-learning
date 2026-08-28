package com.aathithiyan.spring_demo.specification;

import com.aathithiyan.spring_demo.model.Employee;
import org.springframework.data.jpa.domain.Specification;

public class EmployeeSpecification {

    public static Specification<Employee> hasName(String name) {

        return (root, query, criteriaBuilder) ->
                name == null || name.isBlank()
                        ? null
                        : criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("name")),
                        "%" + name.toLowerCase() + "%"
                );
    }

    public static Specification<Employee> hasDepartmentId(
            Integer departmentId) {

        return (root, query, criteriaBuilder) ->
                departmentId == null
                        ? null
                        : criteriaBuilder.equal(
                        root.get("department").get("id"),
                        departmentId
                );
    }

    public static Specification<Employee> salaryGreaterThanOrEqual(
            Double minSalary) {

        return (root, query, criteriaBuilder) ->
                minSalary == null
                        ? null
                        : criteriaBuilder.greaterThanOrEqualTo(
                        root.get("salary"),
                        minSalary
                );
    }

    public static Specification<Employee> salaryLessThanOrEqual(
            Double maxSalary) {

        return (root, query, criteriaBuilder) ->
                maxSalary == null
                        ? null
                        : criteriaBuilder.lessThanOrEqualTo(
                        root.get("salary"),
                        maxSalary
                );
    }
}