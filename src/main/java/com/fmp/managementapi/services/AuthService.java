package com.fmp.managementapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.fmp.managementapi.helpers.CryptHelper;
import com.fmp.managementapi.helpers.JwtTokenHelper;
import com.fmp.managementapi.interfaces.LoginForm;
import com.fmp.managementapi.model.Teacher;
import com.fmp.managementapi.repository.TeacherRepository;

@Service
public class AuthService implements UserDetailsService {
	@Autowired
	private ApplicationContext context;

	@Autowired
	private TeacherRepository teacherRepository;

	@Autowired
	private JwtTokenHelper tokenHelper;

	private AuthenticationManager authenticationManager;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return teacherRepository.findByUsername(username);
	}

	public ResponseEntity<Object> login(@RequestBody LoginForm data) {
		authenticationManager = context.getBean(AuthenticationManager.class);

		var usernamePassword = new UsernamePasswordAuthenticationToken(data.getUsername(), data.getPassword());
		var auth = this.authenticationManager.authenticate(usernamePassword);
		var token = tokenHelper.generateToken((Teacher) auth.getPrincipal());

		return ResponseEntity.ok(token);
	}

	public ResponseEntity<Object> register(@RequestBody Teacher teacher) {
		if (this.teacherRepository.findByUsername(teacher.getUsername()) != null)
			return ResponseEntity.badRequest().build();

		final String hashedPassword = CryptHelper.encode(teacher.getPassword());
		teacher.setPassword(hashedPassword);
		teacherRepository.save(teacher);

		return ResponseEntity.ok().build();
	}
}