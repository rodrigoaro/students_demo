package com.restsimple.demo.service.impl;

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


    /*public CreatedStudentDTO updateStudent(StudentDTO studentDTO) {
        Optional<Student> optStudent = studentRepository.findById(studentDTO.getId());
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

    }*/
    
}
