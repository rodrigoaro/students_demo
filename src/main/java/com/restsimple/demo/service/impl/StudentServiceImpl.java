package com.restsimple.demo.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restsimple.demo.entity.Student;
import com.restsimple.demo.entity.Address;
import com.restsimple.demo.exception.StudentNotFoundException;
import com.restsimple.demo.repository.StudentRepository;
import com.restsimple.demo.service.StudentService;
import com.restsimple.demo.utils.JWTUtils;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private JWTUtils jwtUtil;


    public Student createStudent(Student student){
        student.setToken(jwtUtil.generateStudentToken(student.getEmail()));
        student.setCreated(new Date());
        student.setLastLogin(new Date());
        student.setModified(new Date());
        return studentRepository.save(student);
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


    public Student updateStudent(Student student) {
        Optional<Student> optStudent = studentRepository.findById(student.getId());
        if(optStudent.isEmpty()){
            throw new StudentNotFoundException("El estudiante no se encuentra en la base de datos");
        }

        Student studentToUpdate = optStudent.get();
        studentToUpdate.setName(student.getName());
        studentToUpdate.setEmail(student.getEmail());
        studentToUpdate.setPassword(student.getPassword());
        studentToUpdate.setToken(student.getToken());
        Set<Address> addresses = new HashSet<>();
        addresses.addAll(student.getAddresses());
        addresses.forEach((address) -> address.setStudentId(student.getId()));
        studentToUpdate.setModified(new Date());

        return studentRepository.save(studentToUpdate);

    }
    
}
