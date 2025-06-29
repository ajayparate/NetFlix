package com.netflix.clone.payment.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.netflix.clone.payment.dto.PaymentRequestDTO;
import com.netflix.clone.payment.dto.PaymentResponseDTO;

@Service
public class PaymentService {
    public PaymentResponseDTO processPayment(PaymentRequestDTO request){
        //Simulate integration with Razorpay or Stripe
        PaymentResponseDTO response = new PaymentResponseDTO();
        response.setSuccess(true);
        response.setTransactionId(UUID.randomUUID().toString());
        response.setMessage("Payment successful for user: " + request.getUserId() + " with plan: " + request.getPlanId());
        return response;
    }

}
