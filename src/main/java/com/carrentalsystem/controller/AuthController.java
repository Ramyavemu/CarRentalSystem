package com.carrentalsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carrentalsystem.dto.AuthResponse;
import com.carrentalsystem.dto.LoginRequest;
import com.carrentalsystem.dto.RegisterRequest;
import com.carrentalsystem.service.UserService;

@CrossOrigin(origins = "http://localhost:5175")
@RestController
@RequestMapping("/auth")
public class AuthController {
	
	 @Autowired
	    private UserService service;

	    @PostMapping("/register")
	    public String register(@RequestBody RegisterRequest request) {
	    	System.out.println("register api hit");
	        return service.register(request);
	    }

	    @PostMapping("/login")
	    public AuthResponse login(@RequestBody LoginRequest request) {
	        return service.login(request);
	    }
	    
	    

	        @GetMapping("/success")
	        public String success() {
	            return "Google Login Success";
	        }
	    }

