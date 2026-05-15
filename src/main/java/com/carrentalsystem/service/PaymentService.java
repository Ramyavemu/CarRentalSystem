package com.carrentalsystem.service;

import java.util.List;

import com.carrentalsystem.dto.PaymentVerificationRequest;
import com.carrentalsystem.entity.Payment;

public interface PaymentService {

	Payment makePayment(Long bookingId);

	String createOrder(Long bookingId) throws Exception;
	
	String verifyPayment(PaymentVerificationRequest request) throws Exception;

	 List<Payment> getAllPayments();
}
