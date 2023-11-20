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
import com.restsimple.demo.dto.StudentDTO;
import com.restsimple.demo.service.StudentService;

import jakarta.validation.Valid;


@RestController
@RequestMapping(value = "api/students")
public class StudentController {
    
    @Autowired
    private StudentService studentService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<CreatedStudentDTO> createStudent(@Valid @RequestBody StudentDTO studentDTO) throws Exception{
        CreatedStudentDTO createdStudent = studentService.createStudent(studentDTO);
        createdStudent.getAddresses().forEach((addressDTO) -> addressDTO.setStudentId(createdStudent.getId()));

        CreatedStudentDTO createdStudentDTO = modelMapper.map(createdStudent, CreatedStudentDTO.class);
        return new ResponseEntity<CreatedStudentDTO>(createdStudentDTO, HttpStatus.CREATED);
    }

    @GetMapping("{idStudent}")
    public ResponseEntity<CreatedStudentDTO> getStudentById(@PathVariable("idStudent") UUID studentId) throws Exception{
        CreatedStudentDTO createdStudentDTO = studentService.getStudentById(studentId);
        return new ResponseEntity<CreatedStudentDTO>(createdStudentDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CreatedStudentDTO>> getAllStudents() throws Exception{
        List<CreatedStudentDTO> listStudents = studentService.getAllStudents();
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
