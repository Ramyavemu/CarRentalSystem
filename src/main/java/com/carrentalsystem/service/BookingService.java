package com.carrentalsystem.service;

import java.util.List;

import com.carrentalsystem.entity.Booking;
import com.carrentalsystem.entity.Car;

public interface BookingService {

    Booking bookCar(Long carId, String email, Booking booking);

    List<Booking> getBookingsByUser(String email);

    Booking confirmBooking(Long id);

    Booking pickupCar(Long id, String email);

    Booking returnCar(Long id, String email);

    Booking cancelBooking(Long id, String email);

    Booking getBookingById(Long id);

    List<Booking> getAllBookings();
}