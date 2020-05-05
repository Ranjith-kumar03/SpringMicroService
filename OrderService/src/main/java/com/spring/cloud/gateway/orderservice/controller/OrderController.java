package com.spring.cloud.gateway.orderservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.cloud.gateway.orderservice.common.Payment;
import com.spring.cloud.gateway.orderservice.common.TransactionRequest;
import com.spring.cloud.gateway.orderservice.common.TransactionResponse;
import com.spring.cloud.gateway.orderservice.entity.Order;
import com.spring.cloud.gateway.orderservice.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService _orderService;
	
	
	@PostMapping("/bookorder")
	public TransactionResponse bookOrder(@RequestBody TransactionRequest request)
	{
		
		return _orderService.saveOrder(request);
	}
}
