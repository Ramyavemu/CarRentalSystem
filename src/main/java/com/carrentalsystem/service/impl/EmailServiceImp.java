package com.carrentalsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.carrentalsystem.service.EmailService;

@Service
public class EmailServiceImp implements EmailService{

	    @Autowired
	    private JavaMailSender mailSender;

	    @Override
	    public void sendBookingConfirmation(String to, String userName, String carDetails) {

	        System.out.println("EMAIL SENDING STARTED");

	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setTo(to);
	        message.setSubject("Booking Confirmation");
	        message.setText(
	            "Hi " + userName + ",\n\n" +
	            "Your booking is confirmed.\n" +
	            "Car: " + carDetails + "\n\n" +
	            "Thank you for using our service!"
	        );
	        try {
	            mailSender.send(message);
	            System.out.println("EMAIL SENT SUCCESSFULLY");
	        } catch (Exception e) {
	            System.out.println("EMAIL FAILED");
	            e.printStackTrace();
	        }

	       
	    }
	}

