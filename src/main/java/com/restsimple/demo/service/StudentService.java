package com.restsimple.demo.service;

import java.util.List;
import java.util.UUID;

import com.restsimple.demo.entity.Student;

public interface StudentService {

    Student createStudent(Student student);
    List<Student> getAllStudents();
    Student getStudentById(UUID fromString);
    public Student updateStudent(Student student);

}
