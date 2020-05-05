package com.spring.cloud.gateway.orderservice.common;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="PAYMENT_TBL" )
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int paymentId;
	private String paymentStatus;
	private String transactionId;
	private int orderid;
	private double amount;

}
