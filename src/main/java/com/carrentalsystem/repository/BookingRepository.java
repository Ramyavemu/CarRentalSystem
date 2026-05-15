package com.carrentalsystem.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.carrentalsystem.entity.Booking;
import com.carrentalsystem.enums.Bookingstatus;

import jakarta.transaction.Transactional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("SELECT b FROM Booking b WHERE b.car.id = :carId AND " +
           "(:startDate < b.endDate AND :endDate > b.startDate)")
    List<Booking> findOverlappingBookings(
            @Param("carId") Long carId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    List<Booking> findByUserEmail(String email);
    @Transactional
    @Modifying
    @Query("DELETE FROM Booking b WHERE b.car.id = :carId")
    void deleteBookingsByCarId(@Param("carId") Long carId);
    long countByStatus(Bookingstatus status);
    
    List<Booking> findByUserEmailOrderByIdDesc(String email);
    
    
}