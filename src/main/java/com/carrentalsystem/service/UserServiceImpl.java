package com.carrentalsystem.service;



import com.carrentalsystem.dto.*;
import com.carrentalsystem.entity.*;
import com.carrentalsystem.repository.UserRepository;
import com.carrentalsystem.security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public String register(RegisterRequest request) {

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(encoder.encode(request.getPassword()));

        if (request.getRole() == null || request.getRole().isEmpty()) {
            user.setRole(Role.ROLE_USER);
        } else {
            user.setRole(Role.valueOf(request.getRole()));
        }

//        user.setRole(Role.ROLE_USER);
        repo.save(user);

        return "User Registered Successfully";
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        User user = repo.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!encoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid Password");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return new AuthResponse(token, user.getRole().name());
    }
}

	

