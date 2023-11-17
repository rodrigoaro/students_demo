package com.restsimple.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.List;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import com.restsimple.demo.dto.CreatedStudentDTO;
import com.restsimple.demo.dto.StudentDTO;
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

	@Mock
	private ModelMapper modelMapper;

	private StudentDTO studentDTO;

	private Student student;

	@BeforeEach
	void setUp(){
		MockitoAnnotations.openMocks(this);
		studentDTO = new StudentDTO();
		studentDTO.setName("Rodrigo");
		studentDTO.setEmail("rodrigo@correo.cl");
		studentDTO.setPassword("12345");
		studentDTO.setToken("2WDGV3Y8EGFD8434FDJ3849J38H29BUY3BE");	
		List<Address> addressList = new ArrayList<Address>();
		Address address = new Address(null, "Av. Siempre Viva", "1223", "11", null);
		addressList.add(address);
		studentDTO.setAddresses(addressList);

		student = new Student();
		studentDTO.setName("Pepe");
		studentDTO.setEmail("pepe@correo.cl");
		studentDTO.setPassword("54321");
		studentDTO.setToken("2WDGV3Y8EGFD8434FDJ3849J38H29BUY3BE");
	}

	@Test
	public void testSaveStudent(){

		assertNotNull(studentDTO);
		Student studentEntity = modelMapper.map(studentDTO, Student.class);
		when(studentRepo.save(studentEntity)).thenReturn(studentEntity);

		//CreatedStudentDTO studentCreatedDTO = studentServiceImpl.createStudent(studentDTO);
		//assertNotNull(studentCreatedDTO);
		//assertEquals(studentCreatedDTO.getName(), "Rodrigo");

	}

	/*@Test
	public void testGetAllStudents(){
		List<Student> listStudent = new ArrayList<>();
		listStudent.add(0, student);
		when(studentRepo.findAll()).thenReturn(listStudent);

		List<Student> students = studentServiceImpl.getAllStudents();
		System.out.println(students);
		assertNotNull(students);
		assertEquals(students.get(0).getName(), "Pepe");

	}*/

	/*@Test
	public void testGetStudentById(){
		when(studentRepo.findById(UUID.fromString("bc048c29-ed2a-43e4-9d01-b5ad63e382f5"))).thenReturn(Optional.of(student));
		Student oneStudent = studentServiceImpl.getStudentById(UUID.fromString("bc048c29-ed2a-43e4-9d01-b5ad63e382f5"));
		assertNotNull(oneStudent);
		assertEquals(oneStudent.getEmail(), "rodrigo@correo.cl");
	}*/

	/*@Test
	public void testUpdateStudent(){
		Address address = new Address();
		address.setStudentId(UUID.fromString("bc048c29-ed2a-43e4-9d01-b5ad63e382f5"));
		Set<Address> addresses = new HashSet<>();
		addresses.add(address);
		Student newStudent = new Student();
		newStudent.setName("Franco");
		newStudent.setEmail("franco@correo.cl");
		newStudent.setAddresses(addresses);

		//when(studentRepo.findById(UUID.fromString("bc048c29-ed2a-43e4-9d01-b5ad63e382f5"))).thenReturn(Optional.of(student));
		Student oneStudent = studentServiceImpl.getStudentById(UUID.fromString("bc048c29-ed2a-43e4-9d01-b5ad63e382f5"));
		//when(studentRepo.save(student)).thenReturn(student);

		System.out.println(oneStudent.getId());
		oneStudent.setId(UUID.fromString("bc048c29-ed2a-43e4-9d01-b5ad63e382f5"));
		oneStudent.setName(newStudent.getName());
		oneStudent.setEmail(newStudent.getEmail());
		
		Student updatedStudent = studentServiceImpl.updateStudent(oneStudent);

		assertNotNull(updatedStudent);
		assertEquals(updatedStudent.getEmail(), "franco@correo.cl");
		assertEquals(updatedStudent.getName(), "Franco");

	}*/

}
