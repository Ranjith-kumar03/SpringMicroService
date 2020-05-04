package com.spring.cloud.gateway.orderservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.cloud.gateway.orderservice.entity.Order;
import com.spring.cloud.gateway.orderservice.repository.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository _orderRepo;
	
	public Order saveOrder(Order order)
	{
		return _orderRepo.save(order);
	}

}
