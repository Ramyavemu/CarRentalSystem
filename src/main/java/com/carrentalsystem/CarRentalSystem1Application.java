package com.carrentalsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;

@SpringBootApplication(scanBasePackages = "com.carrentalsystem")
public class CarRentalSystem1Application {

	public static void main(String[] args) {
		SpringApplication.run(CarRentalSystem1Application.class, args);
	}
	@PostConstruct
	public void check() {
	    System.out.println("APP STARTING ON CUSTOM CONFIG");
	}

}
