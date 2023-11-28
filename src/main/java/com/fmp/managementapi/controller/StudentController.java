package com.fmp.managementapi.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fmp.managementapi.model.Student;
import com.fmp.managementapi.repository.StudentRepository;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/users")
public class StudentController {
	@Autowired
	private StudentRepository studentRepository;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Student createStudent(@RequestBody Student student) {
		return studentRepository.save(student);
	}
	
	@PatchMapping
	@ResponseStatus(HttpStatus.OK)
	public Student updateStudent(@RequestBody Student student) throws Exception {
		if(student.getId() == null) throw new Exception();
		return studentRepository.save(student);
	}
	
	@DeleteMapping
	@ResponseStatus(HttpStatus.ACCEPTED) 
	public void deleteStudent(@RequestBody Student student) {
		studentRepository.delete(student);
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.ACCEPTED)
	public List<Student> getStudents() {
		return studentRepository.findAll();
	}
}
