package com.carrentalsystem.dto;

public class CarRequest {


	    private String brand;
	    private String model;
	    private String location;
	    private Double pricePerDay;
	    private boolean available;
	    private String imageUrl;
	    private Integer seatingCapacity;
	    private String fuelType;
	    private Double rating;
		public CarRequest(String brand, String model, String location, Double pricePerDay, boolean available,
				String imageUrl, Integer seatingCapacity, String fuelType, Double rating) {
			super();
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
		public CarRequest() {
			super();
		}
		@Override
		public String toString() {
			return "CarRequest [brand=" + brand + ", model=" + model + ", location=" + location + ", pricePerDay="
					+ pricePerDay + ", available=" + available + ", imageUrl=" + imageUrl + ", seatingCapacity="
					+ seatingCapacity + ", fuelType=" + fuelType + ", rating=" + rating + "]";
		}
		
	
	    
	    
}
