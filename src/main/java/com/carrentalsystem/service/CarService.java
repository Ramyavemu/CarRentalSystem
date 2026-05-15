package com.carrentalsystem.service;

import java.util.List;

import com.carrentalsystem.entity.Car;


public interface CarService {
	void deleteCar(Long id);
	 Car addCar(Car car);
	    List<Car> getAllCars();
	    Car updateCar(Long id, Car car);
		
}
