package com.BikkadIT.SpringBootCurdWithRestfullHibernet.service;

import java.util.List;

import com.BikkadIT.SpringBootCurdWithRestfullHibernet.model.Employee;

public interface ServiceI {

	public Integer saveEmployee(Employee employee);
	
	public String saveAllEmployees(List<Employee> employees);
	
	public Employee getById(Integer empId);
	
	public List<Employee> getAllEmployees();
	
	public List<Employee> getAgeLessThan(int Age);
	
	public Employee update(Employee employee);
	
	public List<Employee> updateAll(List<Employee> employees);
	
	public Employee LoginCheck(String email,int eid);
	
	public Employee DeletebyId(int id);
	
	public String DeleteAll();
}
