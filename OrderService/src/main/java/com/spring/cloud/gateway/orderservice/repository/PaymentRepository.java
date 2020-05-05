package com.spring.cloud.gateway.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.cloud.gateway.orderservice.common.Payment;
import com.spring.cloud.gateway.orderservice.entity.Order;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}
