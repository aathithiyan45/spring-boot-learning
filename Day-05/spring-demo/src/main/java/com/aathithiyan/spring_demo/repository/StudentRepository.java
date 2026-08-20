package com.aathithiyan.spring_demo.repository;

import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {

    public String getStudent() {
        return "Student data from repository";
    }
}