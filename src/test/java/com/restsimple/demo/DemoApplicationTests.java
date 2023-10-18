package com.restsimple.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.restsimple.demo.entity.Address;
import com.restsimple.demo.entity.Student;
import com.restsimple.demo.repository.StudentRepository;
import com.restsimple.demo.service.impl.StudentServiceImpl;
import com.restsimple.demo.utils.JWTUtils;

@SpringBootTest
class DemoApplicationTests {

	@Mock
	private StudentRepository studentRepo;

	@Mock
	private JWTUtils jwtUtil;

	@InjectMocks
	private StudentServiceImpl studentServiceImpl;

	private Student student;

	@BeforeEach
	void setUp(){
		MockitoAnnotations.openMocks(this);
		student = new Student();
		student.setName("Rodrigo");
		student.setEmail("rodrigo@correo.cl");
		student.setPassword("12345");
		student.setToken("2WDGV3Y8EGFD8434FDJ3849J38H29BUY3BE");	
		Set<Address> addressList = new HashSet<>();
		//Address address = new Address("Av. Siempre Viva", "1223", "11");
		//addressList.add(address);
		student.setAddresses(addressList);
	}

	@Test
	public void createStudentShould(){

		assertNotNull(student);
		when(studentRepo.save(student)).thenReturn(student);
		//Student studentOK = studentRepo.save(student);
		
		/*String studentSaved = studentService.createStudent(studentOK);
		System.out.println(studentSaved);
		assertNotNull(studentSaved);*/
	}

	/**
	 * 
	 */
	@Test
	public void testGetAllStudents(){
		List<Student> listStudent = new ArrayList<>();
		listStudent.add(0, student);
		when(studentRepo.findAll()).thenReturn(listStudent);

		List<Student> students = studentServiceImpl.getAllStudents();
		System.out.println(students);
		assertNotNull(students);
		assertEquals(students.get(0).getName(), "Rodrigo");

	}

	@Test
	public void testGetStudentById(){
		when(studentRepo.findById(UUID.fromString("bc048c29-ed2a-43e4-9d01-b5ad63e382f5"))).thenReturn(Optional.of(student));
		Student oneStudent = studentServiceImpl.getStudentById(UUID.fromString("bc048c29-ed2a-43e4-9d01-b5ad63e382f5"));
		assertNotNull(oneStudent);
		assertEquals(oneStudent.getEmail(), "rodrigo@correo.cl");
	}

	@Test
	public void testUpdateStudent(){
		

	}

}
