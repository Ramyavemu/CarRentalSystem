package com.carrentalsystem.dto;

	public class DashboardStatsDTO {

	    private Long totalCars;
	    private Long totalBookings;
	    private Long totalPayments;
	    private Double totalRevenue;

	    public DashboardStatsDTO() {
	    }

	    public DashboardStatsDTO(
	            Long totalCars,
	            Long totalBookings,
	            Long totalPayments,
	            Double totalRevenue) {
	        this.totalCars = totalCars;
	        this.totalBookings = totalBookings;
	        this.totalPayments = totalPayments;
	        this.totalRevenue = totalRevenue;
	    }

	    public Long getTotalCars() {
	        return totalCars;
	    }

	    public void setTotalCars(Long totalCars) {
	        this.totalCars = totalCars;
	    }

	    public Long getTotalBookings() {
	        return totalBookings;
	    }

	    public void setTotalBookings(Long totalBookings) {
	        this.totalBookings = totalBookings;
	    }

	    public Long getTotalPayments() {
	        return totalPayments;
	    }

	    public void setTotalPayments(Long totalPayments) {
	        this.totalPayments = totalPayments;
	    }

	    public Double getTotalRevenue() {
	        return totalRevenue;
	    }

	    public void setTotalRevenue(Double totalRevenue) {
	        this.totalRevenue = totalRevenue;
	    }
	}
	
	

