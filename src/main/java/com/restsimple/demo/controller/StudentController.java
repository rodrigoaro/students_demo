package com.restsimple.demo.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restsimple.demo.entity.Student;
import com.restsimple.demo.service.AddressService;
import com.restsimple.demo.service.StudentService;


@RestController
@RequestMapping(value = "api/students")
public class StudentController {
    
    @Autowired
    private StudentService studentService;

    @Autowired
    private AddressService addressService;

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) throws Exception{
        Student createdStudent = studentService.createStudent(student);
        createdStudent.getAddresses().forEach((address) -> address.setStudentId(student.getId()));
        createdStudent.getAddresses().stream().forEach((address) -> { addressService.createAddress(address); });
        return new ResponseEntity<Student>(createdStudent, HttpStatus.CREATED);
    }

    @GetMapping("{idStudent}")
    public ResponseEntity<Student> getStudentById(@PathVariable("idStudent") UUID studentId) throws Exception{
        Student student = studentService.getStudentById(studentId);
        return new ResponseEntity<Student>(student, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() throws Exception{
        List<Student> listStudents = studentService.getAllStudents();
        return new ResponseEntity<>(listStudents, HttpStatus.OK);
    }

    



}
