package com.spring.cloud.gateway.paymentservice.service;

import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import com.spring.cloud.gateway.paymentservice.entity.Payment;
import com.spring.cloud.gateway.paymentservice.repository.PaymentRepository;


@Service
public class PaymentService {
	
	
	@Autowired
	private PaymentRepository _paymentRepository;
	
	public Payment doPayment(Payment payment)
	{
		payment.setPaymentStatus(paymentProcessing());
		payment.setTransactionId(UUID.randomUUID().toString());
		return _paymentRepository.save(payment);
		
	}
	
	public String paymentProcessing()
	{
		return new Random().nextBoolean() ? "sucess":"failure";
	}

	public Payment findPaymentHistoryByOrderId(int orderid) {
		// TODO Auto-generated method stub
		return _paymentRepository.findByorderid(orderid);
	}


}
