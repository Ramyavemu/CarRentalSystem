package com.carrentalsystem.entity;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "payment")
public class Payment {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String paymentId;
	    private double amount;
	    private String status;

	    @OneToOne
	    @JoinColumn(name = "booking_id")
	  
	   
	    private Booking booking;
	   

		public Payment() {
			super();
		 }

		public Payment(Long id, String paymentId, double amount, String status, Booking booking) {
			super();
			this.id = id;
			this.paymentId = paymentId;
			this.amount = amount;
			this.status = status;
			this.booking = booking;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getPaymentId() {
			return paymentId;
		}

		public void setPaymentId(String paymentId) {
			this.paymentId = paymentId;
		}

		public double getAmount() {
			return amount;
		}

		public void setAmount(double amount) {
			this.amount = amount;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public Booking getBooking() {
			return booking;
		}

		public void setBooking(Booking booking) {
			this.booking = booking;
		}

		@Override
		public String toString() {
			return "Payment [id=" + id + ", paymentId=" + paymentId + ", amount=" + amount + ", status=" + status
					+ ", booking=" + booking + "]";
		}
	    
	    
	    
	    
	}

