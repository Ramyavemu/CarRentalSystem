

package com.carrentalsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.carrentalsystem.dto.PaymentVerificationRequest;
import com.carrentalsystem.entity.Payment;
import com.carrentalsystem.service.PaymentService;

@RestController
@RequestMapping("/payment")
@CrossOrigin(origins = "http://localhost:5173")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/pay/{bookingId}")
    public Payment makePayment(@PathVariable Long bookingId) {
        return paymentService.makePayment(bookingId);
    }

    @PostMapping("/create-order/{bookingId}")
    public String createOrder(@PathVariable Long bookingId) throws Exception {
        return paymentService.createOrder(bookingId);
    }

    @PostMapping("/verify")
    public String verifyPayment(
            @RequestBody PaymentVerificationRequest request
    ) throws Exception {
    	 System.out.println("VERIFY API CALLED");
         String result = paymentService.verifyPayment(request);
         System.out.println("VERIFY RESULT: " + result);
        return result;
    }
    
    @GetMapping("/all")
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }
}