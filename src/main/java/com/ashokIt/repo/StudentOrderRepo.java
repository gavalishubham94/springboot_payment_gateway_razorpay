package com.ashokIt.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashokIt.dto.StudentOrder;

public interface StudentOrderRepo extends JpaRepository<StudentOrder, Integer>{

	public StudentOrder findByRazorpayOrderId(String orderId);
	
}
