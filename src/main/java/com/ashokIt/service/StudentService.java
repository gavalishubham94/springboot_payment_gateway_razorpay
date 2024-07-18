package com.ashokIt.service;

import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ashokIt.dto.StudentOrder;
import com.ashokIt.repo.StudentOrderRepo;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Service
public class StudentService {

	@Autowired
	private StudentOrderRepo studentRepo;
	
	@Value("${razorpay.key.id}")
	private String razorpayKey;
	
	@Value("${razorpay.secret.key}")
	private String razorPaySecret;
	
	private RazorpayClient client;
	
	public StudentOrder createOrder(StudentOrder stuOrder) throws RazorpayException {
		JSONObject orderReq=new JSONObject();
		orderReq.put("amount", stuOrder.getAmount()*100); //amount is paisa
		orderReq.put("currency", "INR");
		orderReq.put("receipt", stuOrder.getEmail());
		
		this.client=new RazorpayClient(razorpayKey, razorPaySecret);
		//create order in zarorpay
		Order razorpayOrder = client.orders.create(orderReq);
		System.out.println(razorpayOrder);
		stuOrder.setRazorpayOrderId(razorpayOrder.get("id"));
		stuOrder.setOrderStatus(razorpayOrder.get("status"));
		studentRepo.save(stuOrder);
		return stuOrder;
	}
	
	public StudentOrder updateOrder(Map<String, String> responsePayLoad) {
		String razorPayOrderId = responsePayLoad.get("razorpay_order_id");
		StudentOrder order= studentRepo.findByRazorpayOrderId(razorPayOrderId);
		order.setOrderStatus("PAYMENT_COMPLETED");
		StudentOrder updatedOrder = studentRepo.save(order);
		return updatedOrder;
	}
}
