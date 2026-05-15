package com.carrentalsystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;
    private String model;
    private String location;
    private Double pricePerDay;

    private boolean available;

    @Column(nullable = false,length = 1000)
    private String imageUrl;   

    private Integer seatingCapacity;
    private String fuelType;
    private Double rating;

   

    public Car() {
		super();
	}

	public Car(Long id, String brand, String model, String location,
               Double pricePerDay, boolean available,
               String imageUrl, Integer seatingCapacity,
               String fuelType, Double rating) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.location = location;
        this.pricePerDay = pricePerDay;
        this.available = available;
        this.imageUrl = imageUrl;
        this.seatingCapacity = seatingCapacity;
        this.fuelType = fuelType;
        this.rating = rating;
    }

   
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Double getPricePerDay() {
		return pricePerDay;
	}

	public void setPricePerDay(Double pricePerDay) {
		this.pricePerDay = pricePerDay;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Integer getSeatingCapacity() {
		return seatingCapacity;
	}

	public void setSeatingCapacity(Integer seatingCapacity) {
		this.seatingCapacity = seatingCapacity;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "Car [id=" + id + ", brand=" + brand + ", model=" + model + ", location=" + location + ", pricePerDay="
				+ pricePerDay + ", available=" + available + ", imageUrl=" + imageUrl + ", seatingCapacity="
				+ seatingCapacity + ", fuelType=" + fuelType + ", rating=" + rating + "]";
	}
    
    
}