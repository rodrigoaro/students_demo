package com.restsimple.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.restsimple.demo.dto.CreatedStudentDTO;
import com.restsimple.demo.entity.Student;
import com.restsimple.demo.service.AddressService;
import com.restsimple.demo.service.StudentService;

import jakarta.validation.Valid;


@RestController
@RequestMapping(value = "api/students")
public class StudentController {
    
    @Autowired
    private StudentService studentService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<CreatedStudentDTO> createStudent(@Valid @RequestBody Student student) throws Exception{
        Student createdStudent = studentService.createStudent(student);
        createdStudent.getAddresses().forEach((address) -> address.setStudentId(student.getId()));
        createdStudent.getAddresses().stream().forEach((address) -> { addressService.createAddress(address); });
        CreatedStudentDTO createdStudentDTO = modelMapper.map(createdStudent, CreatedStudentDTO.class);
        return new ResponseEntity<CreatedStudentDTO>(createdStudentDTO, HttpStatus.CREATED);
        //return new ResponseEntity<Student>(createdStudent, HttpStatus.CREATED);

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
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
