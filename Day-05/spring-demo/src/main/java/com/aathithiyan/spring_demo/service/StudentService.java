package com.aathithiyan.spring_demo.service;

import com.aathithiyan.spring_demo.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public String getStudent() {

        return studentRepository.getStudent();
    }
}