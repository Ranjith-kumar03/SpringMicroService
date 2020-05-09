package com.spring.cloud.gateway.orderservice.service;

import java.net.URI;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.spring.cloud.gateway.orderservice.common.Payment;
import com.spring.cloud.gateway.orderservice.common.TransactionRequest;
import com.spring.cloud.gateway.orderservice.common.TransactionResponse;
import com.spring.cloud.gateway.orderservice.entity.Order;
import com.spring.cloud.gateway.orderservice.repository.OrderRepository;
import com.spring.cloud.gateway.orderservice.repository.PaymentRepository;


@Service
@RefreshScope
public class OrderService {

	
	
	  
	  
	
	  @Value("${microservice.PAYMENT-SERVICE.endpoints.endpoint.uri}")
	  public String ENDPOINT_URL;
	 
	
	@Autowired
	@Lazy
	public RestTemplate restTemplate;
	
	@Autowired
	public PaymentRepository paymentRepository;
	
	@Autowired
	public OrderRepository _orderRepo;
	private String message="";
	
	
	@HystrixCommand(fallbackMethod = "fallBackSaveMethod"
			,threadPoolKey = "OrderPoolKey"
			,threadPoolProperties = {
					@HystrixProperty(name = "coreSize", value = "20"),
                    @HystrixProperty(name = "maxQueueSize", value = "10")

			}   
	        ,commandProperties = {
	        		@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")
            })
	public TransactionResponse saveOrder(TransactionRequest request)
	{
		String url="http://PAYMENT-SERVICE/payment/dopayment";
		
		Order order=request.getOrder();
		Payment payment=request.getPayment();
		payment.setOrderid(order.getId());
		payment.setAmount(order.getPrice());
			
			Payment paymentResponse = restTemplate.postForObject(ENDPOINT_URL, payment, Payment.class);
			paymentRepository.save(paymentResponse);
			_orderRepo.save(order);
			message=paymentResponse.getPaymentStatus().equals("sucess") ? "Transaction happend Sucessfully" : "Transaction Failed or declined";
			
		return new TransactionResponse(order,paymentResponse.getAmount(),paymentResponse.getTransactionId(),message);
	}
	
	public TransactionResponse fallBackSaveMethod(TransactionRequest request)
		{
		System.out.println("Please find the Link :"+ENDPOINT_URL);
		Order order=new Order();
		order.setId(0);
		order.setName("");
		order.setPrice(0.0);
		return new TransactionResponse(order,0.0,"0","failure");
	}

}
