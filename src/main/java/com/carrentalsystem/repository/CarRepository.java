package com.carrentalsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carrentalsystem.entity.Car;

@Repository
public interface CarRepository  extends JpaRepository<Car, Long>{
	List<Car> findByBrandContainingIgnoreCaseAndLocationContainingIgnoreCaseAndFuelTypeContainingIgnoreCaseAndAvailable(
		    String brand,
		    String location,
		    String fuelType,
		    boolean available
		);
}
