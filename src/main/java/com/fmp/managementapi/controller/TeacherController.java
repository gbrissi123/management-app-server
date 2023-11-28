package com.fmp.managementapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fmp.managementapi.helpers.CryptHelper;
import com.fmp.managementapi.model.Teacher;
import com.fmp.managementapi.repository.TeacherRepository;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/teachers")
public class TeacherController {
	@Autowired
	TeacherRepository teacherRepository;
		
	@PostMapping
	private Teacher createTeacher(@RequestBody Teacher teacher) {
		final String hashedPassword = CryptHelper.encode(teacher.getPassword());
		teacher.setPassword(hashedPassword);
		return teacherRepository.save(teacher);
	}
}


//@PostMapping
//@ExceptionHandler();
//private UserDetails login(@RequestBody LoginForm form) throws Exception {
//	final UserDetails user = teacherRepository.findByUsername(form.getUsername());
//	if(!(CryptHelper.compare(form.getPassword(), user.getPassword()))) {
//		throw new Exception("Username or password incorrect");
//	}
//	
//	return user;
//}