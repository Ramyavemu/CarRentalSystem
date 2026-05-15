package com.carrentalsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carrentalsystem.entity.Car;
import com.carrentalsystem.service.CarService;

@RestController
@RequestMapping("/admin/cars")
public class CarController {


	    @Autowired
	    private CarService service;

	   
	    @GetMapping
	    public List<Car> getCars() {
	        return service.getAllCars();
	    }
	    
	}

