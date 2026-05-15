package com.carrentalsystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carrentalsystem.entity.Car;
import com.carrentalsystem.repository.BookingRepository;
import com.carrentalsystem.repository.CarRepository;
import com.carrentalsystem.repository.PaymentRepository;
import com.carrentalsystem.service.CarService;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private BookingRepository bookingRepository;
    
    @Autowired
    private PaymentRepository paymentRepository;

    public CarServiceImpl() {
        System.out.println("CarServiceImpl loaded");
    }

    @Override
    public Car addCar(Car car) {
        return carRepository.save(car);
    }

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public void deleteCar(Long id) {
        paymentRepository.deletePaymentsByCarId(id);
        bookingRepository.deleteBookingsByCarId(id);
        carRepository.deleteById(id);
    }
    
    @Override
    public Car updateCar(Long id, Car updatedCar) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found"));

        car.setBrand(updatedCar.getBrand());
        car.setModel(updatedCar.getModel());
        car.setLocation(updatedCar.getLocation());
        car.setPricePerDay(updatedCar.getPricePerDay());
        car.setAvailable(updatedCar.isAvailable());
        car.setImageUrl(updatedCar.getImageUrl());
        car.setFuelType(updatedCar.getFuelType());
        car.setSeatingCapacity(updatedCar.getSeatingCapacity());
        car.setRating(updatedCar.getRating());

        return carRepository.save(car);
    }
    
}