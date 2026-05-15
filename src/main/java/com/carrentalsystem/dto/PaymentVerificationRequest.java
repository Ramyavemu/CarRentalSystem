package com.carrentalsystem.dto;


public class PaymentVerificationRequest {

	 private String razorpayPaymentId;
	    private String razorpayOrderId;
	    private String razorpaySignature;
	    private Long bookingId;
		public PaymentVerificationRequest() {
			super();
		}
		public PaymentVerificationRequest(String razorpayPaymentId, String razorpayOrderId, String razorpaySignature,
				Long bookingId) {
			super();
			this.razorpayPaymentId = razorpayPaymentId;
			this.razorpayOrderId = razorpayOrderId;
			this.razorpaySignature = razorpaySignature;
			this.bookingId = bookingId;
		}
		public String getRazorpayPaymentId() {
			return razorpayPaymentId;
		}
		public void setRazorpayPaymentId(String razorpayPaymentId) {
			this.razorpayPaymentId = razorpayPaymentId;
		}
		public String getRazorpayOrderId() {
			return razorpayOrderId;
		}
		public void setRazorpayOrderId(String razorpayOrderId) {
			this.razorpayOrderId = razorpayOrderId;
		}
		public String getRazorpaySignature() {
			return razorpaySignature;
		}
		public void setRazorpaySignature(String razorpaySignature) {
			this.razorpaySignature = razorpaySignature;
		}
		public Long getBookingId() {
			return bookingId;
		}
		public void setBookingId(Long bookingId) {
			this.bookingId = bookingId;
		}
		@Override
		public String toString() {
			return "PaymentVerificationRequest [razorpayPaymentId=" + razorpayPaymentId + ", razorpayOrderId="
					+ razorpayOrderId + ", razorpaySignature=" + razorpaySignature + ", bookingId=" + bookingId + "]";
		}
	    
	    
}
