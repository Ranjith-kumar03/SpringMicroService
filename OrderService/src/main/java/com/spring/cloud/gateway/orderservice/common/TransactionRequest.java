package com.spring.cloud.gateway.orderservice.common;

import com.spring.cloud.gateway.orderservice.entity.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {
	
	private Order order;
	private Payment payment;

}
