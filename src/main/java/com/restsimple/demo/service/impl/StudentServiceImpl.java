package com.restsimple.demo.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.restsimple.demo.entity.Student;
import com.restsimple.demo.exception.StudentNotFoundException;
import com.restsimple.demo.repository.StudentRepository;
import com.restsimple.demo.service.StudentService;
import com.restsimple.demo.utils.JWTUtils;

public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private JWTUtils jwtUtil;


    public String createStudent(Student student){
        student.setToken(jwtUtil.generateStudentToken(student.getEmail()));
        Student savedStudent = studentRepository.save(student);
        return "Student created ID -> " + savedStudent.getId();
    }


    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }


    public Student getStudentById(UUID fromString) {
        Optional<Student> optStudent = studentRepository.findById(fromString);
        if(optStudent.isEmpty()){
            throw new StudentNotFoundException("El estudiante no se encuentra en la base de datos");
        }

        return optStudent.get();
    }



    
}
