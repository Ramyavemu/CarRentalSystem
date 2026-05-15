package com.carrentalsystem.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carrentalsystem.enums.Bookingstatus;
import com.carrentalsystem.repository.BookingRepository;
import com.carrentalsystem.repository.CarRepository;
import com.carrentalsystem.repository.PaymentRepository;

@RestController
public class DashboardController {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @GetMapping("/dashboard-counts")
    public Map<String, Long> getDashboardCounts() {

        Map<String, Long> data = new HashMap<>();

        data.put("totalCars", carRepository.count());

        data.put(
            "totalBookings",
            bookingRepository.countByStatus(Bookingstatus.PAID)
        );

        data.put(
            "totalPayments",
            paymentRepository.countByStatus("SUCCESS")
        );

        return data;
    }

    @GetMapping("/total-revenue")
    public Map<String, Double> getTotalRevenue() {

        Map<String, Double> data = new HashMap<>();

        Double revenue = paymentRepository.getTotalRevenue();

        if (revenue == null) {
            revenue = 0.0;
        }

        data.put("totalRevenue", revenue);

        return data;
    }
}