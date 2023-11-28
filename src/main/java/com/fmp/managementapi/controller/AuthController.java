package com.fmp.managementapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fmp.managementapi.interfaces.LoginForm;
import com.fmp.managementapi.model.Teacher;
import com.fmp.managementapi.services.AuthService;

@RestController
@RequestMapping("auth")
public class AuthController {
   
    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginForm loginForm){
        return authService.login(loginForm);
    }


    @PostMapping("/register")
    public ResponseEntity<Object> register (@RequestBody Teacher teacher){
        return authService.register(teacher);
    }
}