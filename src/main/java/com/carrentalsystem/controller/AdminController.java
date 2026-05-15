package com.carrentalsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carrentalsystem.dto.DashboardStatsDTO;
import com.carrentalsystem.repository.BookingRepository;
import com.carrentalsystem.repository.CarRepository;
import com.carrentalsystem.repository.PaymentRepository;

@RestController
public class AdminController {

	

	    @Autowired
	    private CarRepository carRepository;

	    @Autowired
	    private BookingRepository bookingRepository;

	    @Autowired
	    private PaymentRepository paymentRepository;

	    @GetMapping("/admin/dashboard")
	    public DashboardStatsDTO getDashboardStats() {

	        Long totalCars = carRepository.count();
	        Long totalBookings = bookingRepository.count();
	        Long totalPayments = paymentRepository.count();

	        Double totalRevenue = paymentRepository.findAll()
	                .stream()
	                .mapToDouble(payment -> payment.getAmount())
	                .sum();

	        return new DashboardStatsDTO(
	                totalCars,
	                totalBookings,
	                totalPayments,
	                totalRevenue
	        );
	    }
	}

