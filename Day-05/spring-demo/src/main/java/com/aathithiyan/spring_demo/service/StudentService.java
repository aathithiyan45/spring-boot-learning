package com.aathithiyan.spring_demo.service;

import com.aathithiyan.spring_demo.model.Student;
import com.aathithiyan.spring_demo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.getAllStudents();
    }

    public Student getStudentById(int id) {

        return studentRepository.getStudentById(id);
    }

    public Student addStudent(Student student) {
        return studentRepository.addStudent(student);
    }

    public Student updateStudent(int id, Student student) {

        return studentRepository.updateStudent(id, student);
    }
    public Student patchStudent(int id, Student student) {

        return studentRepository.patchStudent(id, student);
    }
    public boolean deleteStudent(int id) {

        return studentRepository.deleteStudent(id);
    }
}