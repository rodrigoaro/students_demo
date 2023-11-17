package com.restsimple.demo.service;

import java.util.List;
import java.util.UUID;

import com.restsimple.demo.dto.CreatedStudentDTO;
import com.restsimple.demo.dto.StudentDTO;

public interface StudentService {

    CreatedStudentDTO createStudent(StudentDTO studentDTO);
    List<CreatedStudentDTO> getAllStudents();
    CreatedStudentDTO getStudentById(UUID fromString);
    //CreatedStudentDTO updateStudent(StudentDTO studentDTO);

}
