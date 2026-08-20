package com.aathithiyan.spring_demo.controller;

import com.aathithiyan.spring_demo.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public String getStudent() {

        return studentService.getStudent();
    }
}