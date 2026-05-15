package com.carrentalsystem.service;

public interface EmailService {
	void sendBookingConfirmation(
            String toEmail,
            String userName,
            String carName
    );
}
