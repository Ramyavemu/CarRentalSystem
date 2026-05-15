
package com.carrentalsystem.service.impl;



import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.carrentalsystem.dto.PaymentVerificationRequest;
import com.carrentalsystem.entity.Booking;
import com.carrentalsystem.entity.Payment;
import com.carrentalsystem.enums.Bookingstatus;
import com.carrentalsystem.repository.BookingRepository;
import com.carrentalsystem.repository.PaymentRepository;
import com.carrentalsystem.service.EmailService;
import com.carrentalsystem.service.PaymentService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.Utils;

import org.json.JSONObject;


@Service
public class PaymentServiceImpl implements PaymentService {

	@Value("${razorpay.key.secret}")
	private String keySecret;
    @Autowired
    private PaymentRepository paymentRepo;

    @Autowired
    private BookingRepository bookingRepo;
    
    @Autowired
    private RazorpayClient razorpayClient;
    
    @Autowired
    private EmailService emailService;

//    @Override
//    public Payment makePayment(Long bookingId) {
//
//        Booking booking = bookingRepo.findById(bookingId)
//                .orElseThrow(() -> new RuntimeException("Booking not found"));
//
//        if (booking.getTotalAmount() == null || booking.getTotalAmount() <= 0) {
//            throw new RuntimeException("Invalid booking amount");
//        }
//
//        Payment payment = new Payment();
//
//        payment.setPaymentId("PAY_" + UUID.randomUUID().toString());
//        payment.setAmount(booking.getTotalAmount());
//        payment.setStatus("SUCCESS");
//        payment.setBooking(booking);
//
//        booking.setStatus(Bookingstatus.PAID);
//        bookingRepo.save(booking);
//
//        return paymentRepo.save(payment);
//    }
    @Override
    public Payment makePayment(Long bookingId) {

        Booking booking = bookingRepo.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        if (booking.getStatus() == Bookingstatus.PAID) {
            throw new RuntimeException("Payment already completed for this booking");
        }

        if (booking.getTotalAmount() == null || booking.getTotalAmount() <= 0) {
            throw new RuntimeException("Invalid booking amount");
        }

        Payment payment = new Payment();

        payment.setPaymentId("PAY_" + UUID.randomUUID().toString());
        payment.setAmount(booking.getTotalAmount());
        payment.setStatus("SUCCESS");
        payment.setBooking(booking);

        booking.setStatus(Bookingstatus.PAID);
        bookingRepo.save(booking);

        return paymentRepo.save(payment);
    }
    
   

    @PostMapping("/create-order/{bookingId}")
    public String createOrder(@PathVariable Long bookingId) throws Exception {

        System.out.println("PAYMENT API HIT");

        Booking booking = bookingRepo.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        System.out.println("Booking Found: " + booking.getId());
        System.out.println("Total Amount: " + booking.getTotalAmount());

        RazorpayClient razorpay = new RazorpayClient(
        		"rzp_test_ShMDcEVdXzg7Xm",
        		"WdIVnthep3UbdqoCZACSwAaY"
        );

        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", booking.getTotalAmount() * 100);
        orderRequest.put("currency", "INR");
        orderRequest.put("receipt", "txn_" + bookingId);

        Order order = razorpay.orders.create(orderRequest);

        System.out.println("Order Created: " + order);

        return order.toString();
    }
    
   
    @Override
    public String verifyPayment(PaymentVerificationRequest request) throws Exception {

        JSONObject options = new JSONObject();

        options.put("razorpay_order_id", request.getRazorpayOrderId());
        options.put("razorpay_payment_id", request.getRazorpayPaymentId());
        options.put("razorpay_signature", request.getRazorpaySignature());

        boolean isValid = Utils.verifyPaymentSignature(
                options,
                keySecret
        );

        if (!isValid) {
            throw new RuntimeException("Payment verification failed");
        }

        Booking booking = bookingRepo.findById(request.getBookingId())
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        if (booking.getStatus() == Bookingstatus.PAID) {
            return "Payment already verified";
        }

        booking.setStatus(Bookingstatus.PAID);
        bookingRepo.save(booking);

        Payment payment = new Payment();
        payment.setPaymentId(request.getRazorpayPaymentId());
        payment.setAmount(booking.getTotalAmount());
        payment.setStatus("SUCCESS");
        payment.setBooking(booking);

        paymentRepo.save(payment);

        // SEND EMAIL
        emailService.sendBookingConfirmation(
                booking.getUser().getEmail(),
                booking.getUser().getName(),
                booking.getCar().getBrand() + " " + booking.getCar().getModel()
        );

        return "Payment verified successfully";
    }



	@Override
	public List<Payment> getAllPayments() {
		// TODO Auto-generated method stub
		 return paymentRepo.findAll();
	}
    
    
    
    
    
    
    
    
    
    
}