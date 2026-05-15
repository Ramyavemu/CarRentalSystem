package com.carrentalsystem.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carrentalsystem.entity.Booking;
import com.carrentalsystem.service.BookingService;

@RestController
@RequestMapping("/bookings")
public class BookingController {

	 @Autowired
	    private BookingService service;

	 @PostMapping("/create/{carId}")
	 public Booking bookCar(@PathVariable Long carId,
	                        @RequestBody Booking booking,
	                        Authentication auth) {

	     if (auth == null) {
	         throw new RuntimeException("Unauthorized request");
	     }

	     String email = auth.getName();

	     System.out.println("BOOK API HIT");
	     System.out.println("EMAIL: " + email);

	     return service.bookCar(carId, email, booking);
	 }
//	    @PostMapping("/create/{carId}")
//	    public Booking bookCar(@PathVariable Long carId,
//	                           @RequestBody Booking booking,
//	                           Authentication auth) {
//
//	    	String email = auth != null ? auth.getName() : null; // from JWT
//	    	System.out.println("BOOK API HIT");
//	        return service.bookCar(carId, email, booking);
//	    }
	 
	 
//	    @GetMapping("/my")
//	    public List<Booking> getMyBookings(Authentication auth) {
//	    	  System.out.println("USER: " + auth.getName());
//	    	    System.out.println("AUTHORITIES: " + auth.getAuthorities());
//	        return service.getBookingsByUser(auth.getName());
//	    }
	    
	    @PutMapping("/admin/{id}/confirm")
	    public Booking confirmBooking(@PathVariable Long id) {
	        return service.confirmBooking(id);
	    }
	    
	    @PutMapping("/{id}/pickup")
	    public Booking pickup(@PathVariable Long id, Authentication auth) {

	        String email = auth.getName();

	        return service.pickupCar(id, email);
	    }
	
	    @PutMapping("/{id}/return")
	    public Booking returnCar(@PathVariable Long id, Authentication auth) {

	        String email = auth.getName();

	        return service.returnCar(id, email);
	    }
	    
	    @PutMapping("/{id}/cancel")
	    public Booking cancelBooking(@PathVariable Long id, Authentication auth) {

	        String email = auth.getName();

	        return service.cancelBooking(id, email);
	    }
	    
	    
	    
	    @GetMapping("/{id}")
	    public Booking getBookingById(@PathVariable Long id) {
	        return service.getBookingById(id);
	    }
	    
	    @GetMapping("/all")
	    public List<Booking> getAllBookings() {
	        return service.getAllBookings();
	    }
	    @PutMapping("/cancel/{bookingId}")
	    public ResponseEntity<?> cancelBooking(@PathVariable Long bookingId,
	                                           Principal principal) {

	        String email = principal.getName();

	        Booking booking = service.cancelBooking(bookingId, email);

	        return ResponseEntity.ok(booking);
	    }
	    @GetMapping("/my")
	    public List<Booking> getMyBookings(Principal principal) {
	        return service.getBookingsByUser(principal.getName());
	    }
	    
}
