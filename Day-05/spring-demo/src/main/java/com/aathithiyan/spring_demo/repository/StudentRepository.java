package com.aathithiyan.spring_demo.repository;

import com.aathithiyan.spring_demo.model.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepository {

    private final List<Student> students = new ArrayList<>();

    public StudentRepository() {
        students.add(new Student(1, "Aathi", 21));
        students.add(new Student(2, "Rahul", 22));
    }

    public List<Student> getAllStudents() {
        return students;
    }
    public Student getStudentById(int id) {

        for (Student student : students) {

            if (student.getId() == id) {
                return student;
            }
        }


        return null;
    }
    public Student addStudent(Student student) {
        students.add(student);
        return student;
    }
    public boolean deleteStudent(int id) {

        for (Student student : students) {

            if (student.getId() == id) {
                students.remove(student);
                return true;
            }
        }

        return false;
    }
    public Student updateStudent(int id, Student updatedStudent) {

        for (int i = 0; i < students.size(); i++) {

            if (students.get(i).getId() == id) {
                students.set(i, updatedStudent);
                return updatedStudent;
            }
        }

        return null;
    }
    public Student patchStudent(int id, Student updatedStudent) {

        for (Student student : students) {

            if (student.getId() == id) {

                if (updatedStudent.getName() != null) {
                    student.setName(updatedStudent.getName());
                }

                if (updatedStudent.getAge() != 0) {
                    student.setAge(updatedStudent.getAge());
                }

                return student;
            }
        }

        return null;
    }
}