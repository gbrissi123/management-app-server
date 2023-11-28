package com.fmp.managementapi.middlewares;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fmp.managementapi.helpers.JwtTokenHelper;
import com.fmp.managementapi.repository.TeacherRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityMiddleware extends OncePerRequestFilter {
	@Autowired
	JwtTokenHelper tokenHelper;
	
	@Autowired
	TeacherRepository teacherRepository;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		var token = this.recoverToken(request);
		
		if(token != null) {
			var username = tokenHelper.validateToken(token);
			UserDetails user = teacherRepository.findByUsername(username);
			
			if(user != null) {
				var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}
		
		filterChain.doFilter(request, response);
	}
	
	private String recoverToken(HttpServletRequest request) {
		var authHeader = request.getHeader("Authorization");
		if(authHeader == null) return null;
		return authHeader.replace("Bearer ", "");
	}
}
