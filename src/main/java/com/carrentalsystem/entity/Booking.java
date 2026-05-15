package com.carrentalsystem.entity;

import java.time.LocalDate;


import com.carrentalsystem.enums.Bookingstatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
@Table(name = "bookings")
public class Booking {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    
	    private Long id;
	    private LocalDate startDate;
	    private LocalDate endDate;
	    
	    @Enumerated(EnumType.STRING)
	    
	    private Bookingstatus status;
	    @ManyToOne
	    private User user;

	    @ManyToOne
	    private Car car;
	   
	   
	    private Double totalAmount;

		public Booking() {
			super();
		}

		
		






		public Booking(Long id, LocalDate startDate, LocalDate endDate, Bookingstatus status, User user, Car car,
				Payment payment, Double totalAmount) {
			super();
			this.id = id;
			this.startDate = startDate;
			this.endDate = endDate;
			this.status = status;
			this.user = user;
			this.car = car;
			
			this.totalAmount = totalAmount;
		}









		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public LocalDate getStartDate() {
			return startDate;
		}

		public void setStartDate(LocalDate startDate) {
			this.startDate = startDate;
		}

		public LocalDate getEndDate() {
			return endDate;
		}

		public void setEndDate(LocalDate endDate) {
			this.endDate = endDate;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public Car getCar() {
			return car;
		}

		public void setCar(Car car) {
			this.car = car;
		}

		
		

		public Double getTotalAmount() {
			return totalAmount;
		}





		public void setTotalAmount(Double totalAmount) {
			this.totalAmount = totalAmount;
		}





		public Bookingstatus getStatus() {
			return status;
		}


		public void setStatus(Bookingstatus status) {
			this.status = status;
		}
		


		@Override
		public String toString() {
			return "Booking [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + ", status=" + status
					+ ", user=" + user + ", car=" + car + ",  totalAmount=" + totalAmount + "]";
		}









		
	    
	    
}