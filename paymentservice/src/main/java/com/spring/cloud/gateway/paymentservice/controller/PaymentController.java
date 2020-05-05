package com.spring.cloud.gateway.paymentservice.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.cloud.gateway.paymentservice.entity.Payment;
import com.spring.cloud.gateway.paymentservice.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	@PostMapping("/dopayment")
	public Payment doPayment(@RequestBody Payment payment )
	{
		System.out.println("Iam getting hooked payment service");
		return paymentService.doPayment(payment);
	}
	
	@GetMapping("/{orderid}")
	public Payment findPaymentHistoryByOrderId(@PathVariable int orderid)
	{
		return paymentService.findPaymentHistoryByOrderId(orderid);
	}
	
	
}
