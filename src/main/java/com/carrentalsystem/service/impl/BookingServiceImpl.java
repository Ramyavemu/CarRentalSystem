package com.carrentalsystem.service.impl;


import com.carrentalsystem.entity.*;

import com.carrentalsystem.enums.Bookingstatus;
import com.carrentalsystem.repository.*;
import com.carrentalsystem.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepo;

    @Autowired
    private CarRepository carRepo;

    @Autowired
    private UserRepository userRepo;

    
    @Override
    public Booking bookCar(Long carId, String email, Booking booking) {

        Car car = carRepo.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car not found"));

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        LocalDate start = booking.getStartDate();
        LocalDate end = booking.getEndDate();

        
        
        
        // ✅ 1. Date validation
        if (start.isAfter(end)) {
            throw new RuntimeException("Invalid dates");
        }
        
        long days = ChronoUnit.DAYS.between(start, end) + 1;
        
        double totalAmount = days * car.getPricePerDay();

        System.out.println("Total Days: " + days);
        System.out.println("Total Price: " + totalAmount);
        
        
        if (start.isBefore(LocalDate.now())) {
            throw new RuntimeException("Start date cannot be in past");
        }

        // ✅ 2. Check overlapping bookings
        List<Booking> conflicts =
                bookingRepo.findOverlappingBookings(carId, start, end);
        System.out.println("Start Date: " + start);
        System.out.println("End Date: " + end);
        System.out.println("Today: " + LocalDate.now());
        System.out.println("Conflicts found: " + conflicts.size());
        if (!conflicts.isEmpty()) {
            throw new RuntimeException("Car already booked for selected dates");
        }

        // ✅ 3. Save booking
        booking.setCar(car);
        booking.setUser(user);
        booking.setStatus(Bookingstatus.BOOKED);
        booking.setTotalAmount(totalAmount);  

        // 🔥 IMPORTANT: mark car unavailable
//        car.setAvailable(false);
//        carRepo.save(car);

        return bookingRepo.save(booking);
    }
    
    
    
    
    
    
    
    public Booking getBookingById(Long id) {
        return bookingRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
    }
    
    
    
    

	@Override
	public List<Booking> getBookingsByUser(String email) {
		// TODO Auto-generated method stub
		//return bookingRepo.findByUserEmail(email);
		return bookingRepo.findByUserEmailOrderByIdDesc(email);
	}
	
	
	public Booking confirmBooking(Long id) {
	    Booking booking = bookingRepo.findById(id)
	            .orElseThrow(() -> new RuntimeException("Booking not found"));

	    if (booking.getStatus() != Bookingstatus.PAID){
	        throw new RuntimeException("Only BOOKED bookings can be confirmed");
	    }

	    booking.setStatus(Bookingstatus.CONFIRMED);
	    return bookingRepo.save(booking);
	}
	
	
	
	
	
	public Booking pickupCar(Long bookingId, String email) {

	    Booking booking = bookingRepo.findById(bookingId)
	            .orElseThrow(() -> new RuntimeException("Booking not found"));


	    if (!booking.getUser().getEmail().equals(email)) {
	        throw new RuntimeException("Unauthorized access");
	    }

	    if (booking.getStatus() != Bookingstatus.CONFIRMED) {
	        throw new RuntimeException("Car can only be picked up after confirmation");
	    }

	    booking.setStatus(Bookingstatus.PICKED_UP);

	    return bookingRepo.save(booking);
	}
	
	
	
	
	
	public Booking returnCar(Long bookingId, String email) {

	    Booking booking = bookingRepo.findById(bookingId)
	            .orElseThrow(() -> new RuntimeException("Booking not found"));

	    if (!booking.getUser().getEmail().equals(email)) {
	        throw new RuntimeException("Unauthorized access");
	    }

	    if (booking.getStatus() != Bookingstatus.PICKED_UP) {
	        throw new RuntimeException("Car must be picked up before returning");
	    }

	    booking.setStatus(Bookingstatus.RETURNED);

	    // ✅ Make car available again
//	    Car car = booking.getCar();
//	    car.setAvailable(true);
//	    carRepo.save(car);

	    return bookingRepo.save(booking);
	}
	
	
	
	
//	
//	public Booking cancelBooking(Long bookingId, String email) {
//
//	    Booking booking = bookingRepo.findById(bookingId)
//	            .orElseThrow(() -> new RuntimeException("Booking not found"));
//
//	    
//	    if (!booking.getUser().getEmail().equals(email)) {
//	        throw new RuntimeException("Unauthorized access");
//	    }
//
//	
//	    if (!booking.getStartDate().isAfter(LocalDate.now())) {
//	        throw new RuntimeException("Cannot cancel on or after start date");
//	    }
//
//	   
//	    if (booking.getStatus() == Bookingstatus.PICKED_UP ||
//	        booking.getStatus() == Bookingstatus.RETURNED) {
//	        throw new RuntimeException("Cannot cancel after pickup/return");
//	    }
//	    
//	    if (booking.getStatus().equals("ACTIVE")) {
//	        throw new RuntimeException("Cannot cancel after pickup");
//	    }
//
//	    booking.setStatus(Bookingstatus.CANCELLED);
//
//	  
//	    booking.getCar().setAvailable(true);
//
//	    return bookingRepo.save(booking);
//	}
	
	public Booking cancelBooking(Long bookingId, String email) {

	    Booking booking = bookingRepo.findById(bookingId)
	            .orElseThrow(() -> new RuntimeException("Booking not found"));

	    // ✅ Check user
	    if (!booking.getUser().getEmail().equals(email)) {
	        throw new RuntimeException("Unauthorized access");
	    }

	    // ✅ Prevent invalid cancel
	    if (booking.getStatus() == Bookingstatus.PICKED_UP ||
	        booking.getStatus() == Bookingstatus.RETURNED) {
	        throw new RuntimeException("Cannot cancel after pickup/return");
	    }

	    // ✅ Optional: prevent cancel after start date
	    if (!booking.getStartDate().isAfter(LocalDate.now())) {
	        throw new RuntimeException("Cannot cancel on or after start date");
	    }

	    // ✅ Update booking status
	    booking.setStatus(Bookingstatus.CANCELLED);

	    // ✅ Make car AVAILABLE again
	    Car car = booking.getCar();
	    car.setAvailable(true);
	    carRepo.save(car);   // ⭐ VERY IMPORTANT

	    return bookingRepo.save(booking);
	}
	@Override
	public List<Booking> getAllBookings() {
	    return bookingRepo.findAll();
	}
	
	
}