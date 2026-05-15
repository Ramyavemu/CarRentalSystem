package com.carrentalsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.carrentalsystem.entity.Payment;

import jakarta.transaction.Transactional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long>{


	    @Query("SELECT COALESCE(SUM(p.amount), 0) FROM Payment p")
	    Double getTotalRevenue();
	    
	    @Transactional
	    @Modifying
	    @Query("""
	        DELETE FROM Payment p
	        WHERE p.booking.id IN (
	            SELECT b.id FROM Booking b WHERE b.car.id = :carId
	        )
	    """)
	    void deletePaymentsByCarId(Long carId);
	    long countByStatus(String status);
	}

