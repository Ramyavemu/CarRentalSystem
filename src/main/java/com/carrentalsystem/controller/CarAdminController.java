package com.carrentalsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.carrentalsystem.dto.CarRequest;
import com.carrentalsystem.entity.Car;
import com.carrentalsystem.repository.CarRepository;
import com.carrentalsystem.service.CarService;

@RestController
@RequestMapping("/admin/cars")
@CrossOrigin
public class CarAdminController {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarService carService;

    @PostMapping("/addcar")
    public Car addCar(@RequestBody CarRequest request) {

        Car car = new Car();

        car.setBrand(request.getBrand());
        car.setModel(request.getModel());
        car.setLocation(request.getLocation());
        car.setPricePerDay(request.getPricePerDay());
        car.setAvailable(request.isAvailable());
        car.setImageUrl(request.getImageUrl());
        car.setSeatingCapacity(request.getSeatingCapacity());
        car.setFuelType(request.getFuelType());
        car.setRating(request.getRating());

        return carRepository.save(car);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCar(@PathVariable("id") Long id) {
        carService.deleteCar(id);
        return ResponseEntity.ok("Car deleted successfully");
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(
            @PathVariable("id") Long id,
            @RequestBody Car car) {

        Car updatedCar = carService.updateCar(id, car);
        return ResponseEntity.ok(updatedCar);
    }
    
    
    
    @GetMapping("/search")
    public List<Car> searchCars(
            @RequestParam(defaultValue = "") String brand,
            @RequestParam(defaultValue = "") String location,
            @RequestParam(defaultValue = "") String fuelType,
            @RequestParam(defaultValue = "true") boolean available
    ) {
        return carRepository
                .findByBrandContainingIgnoreCaseAndLocationContainingIgnoreCaseAndFuelTypeContainingIgnoreCaseAndAvailable(
                        brand,
                        location,
                        fuelType,
                        available
                );
    }
}