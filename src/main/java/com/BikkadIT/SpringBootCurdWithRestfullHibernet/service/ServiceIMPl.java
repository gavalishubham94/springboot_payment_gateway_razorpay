package com.BikkadIT.SpringBootCurdWithRestfullHibernet.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BikkadIT.SpringBootCurdWithRestfullHibernet.Dao.EmployeeDaoI;
import com.BikkadIT.SpringBootCurdWithRestfullHibernet.model.Employee;

@Service
public class ServiceIMPL implements ServiceI{


	@Autowired
	private EmployeeDaoI employeeDaoI;

	@Override
	public Integer saveEmployee(Employee employee) {

		Integer empId = employeeDaoI.saveEmployee(employee);

		return empId;
	}
	
	

	@Override
	public String saveAllEmployees(List<Employee> employees) {
		String msg = employeeDaoI.SaveAllEmployee(employees);
		
		
		return msg;
	}



	@Override
	public Employee getById(Integer empId) {
		Employee employee = employeeDaoI.getById(empId);
		return employee;
	}
	
	public List<Employee> getAllEmployees(){
		List<Employee> employees = employeeDaoI.getAllEmployees();
		
		return employees ;
	
	}



	@Override
	public List<Employee> getAgeLessThan(int Age) {
		List<Employee> list = employeeDaoI.getAgeLessThan(Age);
		return list;
	}



	@Override
	public Employee update(Employee employee) {
		Employee employee2 = employeeDaoI.Update(employee);
		
		return employee2;
	}
	
	public List<Employee> updateAll(List<Employee> employees){
		List<Employee> list = employeeDaoI.updateAll(employees);
		
		return list;

	}
	
	public Employee LoginCheck(String email,int eid) {
		
		
		Employee employee = employeeDaoI.LoginCheck(email, eid);
	
		return employee;
	
	}
	
	public Employee DeletebyId(int id) {
	
		Employee employee = employeeDaoI.DeletebyId(id);
		
		return employee;
	
	}
	
	public String DeleteAll() {
		String msg = employeeDaoI.DeleteAll();
	
		return msg;

	}
	
}
