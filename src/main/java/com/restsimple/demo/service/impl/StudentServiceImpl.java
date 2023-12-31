package com.restsimple.demo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restsimple.demo.entity.Student;
import com.restsimple.demo.dto.AddressDTO;
import com.restsimple.demo.dto.CreatedStudentDTO;
import com.restsimple.demo.dto.StudentDTO;
import com.restsimple.demo.entity.Address;
import com.restsimple.demo.exception.StudentNotFoundException;
import com.restsimple.demo.repository.StudentRepository;
import com.restsimple.demo.service.StudentService;
import com.restsimple.demo.utils.JWTUtils;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private JWTUtils jwtUtil;

    private ModelMapper modelMapper;


    public CreatedStudentDTO createStudent(StudentDTO studentDTO){
        studentDTO.setToken(jwtUtil.generateStudentToken(studentDTO.getEmail()));
        studentDTO.setCreated(new Date());
        studentDTO.setModified(new Date());

        Student student = modelMapper.map(studentDTO, Student.class);
        Student savedStudent = studentRepository.save(student);
        return modelMapper.map(savedStudent, CreatedStudentDTO.class);
    }


    public List<CreatedStudentDTO> getAllStudents() {
        //return studentRepository.findAll();
        List<Student> students = studentRepository.findAll();
        return students.stream()
                        .map((student) -> 
                            modelMapper.map(student, CreatedStudentDTO.class))
                        .collect(Collectors.toList());
    }


    public CreatedStudentDTO getStudentById(UUID fromString) {
        Optional<Student> optStudent = studentRepository.findById(fromString);
        if(optStudent.isEmpty()){
            throw new StudentNotFoundException("El estudiante no se encuentra en la base de datos");
        }
        return modelMapper.map(optStudent.get(), CreatedStudentDTO.class);
    }


    public CreatedStudentDTO updateStudent(StudentDTO studentDTO) {
        Optional<Student> optStudent = studentRepository.findById(studentDTO.getId());
        if(optStudent.isEmpty()){
            throw new StudentNotFoundException("The student is not in our system, please check it.");
        }

        StudentDTO studentToUpdate = modelMapper.map(optStudent.get(), StudentDTO.class);
        studentToUpdate.setName(studentDTO.getName());
        studentToUpdate.setEmail(studentDTO.getEmail());
        studentToUpdate.setPassword(studentDTO.getPassword());
        studentToUpdate.setToken(studentDTO.getToken());
        List<AddressDTO> addresses = new ArrayList<AddressDTO>();
        addresses.addAll(studentDTO.getAddresses());
        addresses.forEach((address) -> address.setStudentId(studentDTO.getId()));
        studentToUpdate.setModified(new Date());

        Student student = modelMapper.map(studentToUpdate, Student.class);
        Student savedStudent = studentRepository.save(student);
        return modelMapper.map(savedStudent, CreatedStudentDTO.class);
    }
    
}
