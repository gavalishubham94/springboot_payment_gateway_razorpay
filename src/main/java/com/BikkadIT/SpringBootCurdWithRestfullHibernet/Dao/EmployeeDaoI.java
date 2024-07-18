package com.BikkadIT.SpringBootCurdWithRestfullHibernet.Dao;

import java.util.List;

import com.BikkadIT.SpringBootCurdWithRestfullHibernet.model.Employee;

public interface EmployeeDaoI {

	public Integer saveEmployee(Employee employee);
	
	public String SaveAllEmployee(List<Employee> employee);
	
	public Employee getById(Integer empId);
	
	public List<Employee> getAllEmployees();
	
	public List<Employee> getAgeLessThan(int Age);
	
	public Employee Update(Employee employee);
	
	public List<Employee> updateAll(List<Employee> employees);
	
	
	public Employee LoginCheck(String email,int eid);
	
	
	public Employee DeletebyId(int id);
	
	public String DeleteAll();
}
