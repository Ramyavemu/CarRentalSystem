package com.carrentalsystem.service;

import org.springframework.stereotype.Service;

import com.carrentalsystem.dto.AuthResponse;
import com.carrentalsystem.dto.LoginRequest;
import com.carrentalsystem.dto.RegisterRequest;

@Service
public interface UserService {

	 String register(RegisterRequest request);
	    AuthResponse login(LoginRequest request);
}
