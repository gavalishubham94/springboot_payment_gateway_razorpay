package com.BikkadIT.SpringBootCurdWithRestfullHibernet.Dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.BikkadIT.SpringBootCurdWithRestfullHibernet.model.Employee;

@Repository
public class EmployeeDaoIMPL implements EmployeeDaoI{

	@Autowired
	private SessionFactory sf;
	
	@Override
	public Integer saveEmployee(Employee employee) {
		Session session = sf.openSession();
		
		session.beginTransaction();
		Integer empId = (Integer) session.save(employee);
		session.getTransaction().commit();
		return empId;
	}

	
	public String SaveAllEmployee(List<Employee> employee){
		Session session = sf.openSession();
		session.beginTransaction();
		
		for(Employee E:employee) {
			
			session.save(E);
			 
		       }
		
		String msg=" All Employees Saved";
		
		session.getTransaction().commit();
		return msg;
		
	}


	@Override
	public Employee getById(Integer empId) {
		Session session = sf.openSession();
		session.beginTransaction();
		
		Employee employee = session.get(Employee.class, empId);
		
		//Hibernate.initialize(employee);
		
		session.getTransaction().commit();
		return employee;
	}
	
	
	@Override
	public List<Employee> getAllEmployees(){
	
		Session session = sf.openSession();
		
		String hql="from Employee";
		Query query = session.createQuery(hql);
		List employees = query.getResultList();
		
		return employees;	
	}
	
	
	
	public List<Employee> getAgeLessThan(int Age){
		
		Session session = sf.openSession();
		
	String hql="from Employee where empAge <=:Age";
		
		Query query = session.createQuery(hql);
		query.setParameter("Age", Age);
		List list = query.getResultList();
		
		return list;

	}
	
	
	public Employee Update(Employee employee) {

		Session session = sf.openSession();
		
		Employee employee2 = session.get(Employee.class, employee.getEmpId());
		session.clear();
		session.beginTransaction();
		if(employee2!=null) {
		
		session.update(employee);
		session.getTransaction().commit();
		session.close();
		return employee;
		
		}
		session.getTransaction().commit();
		session.close();
		return employee2;

	}
	
	
	public List<Employee> updateAll(List<Employee> employees){
		List<Employee> list= new ArrayList();
		
		Session session = sf.openSession();

		session.beginTransaction();

		for(Employee Emp:employees) {
			Employee employee = session.get(Employee.class, Emp.getEmpId());
			session.clear();
				
			if(employee!=null) {
				
				session.update(Emp);
				list.add(Emp);
				
			}	}
			session.getTransaction().commit();
			session.close();
			return list;
	}
	
	
	public Employee LoginCheck(String email,int eid) {

		Session session = sf.openSession();
		
		String hql="from Employee where empEmail=:email and empId=:eid";
		Query query = session.createQuery(hql);
		query.setParameter("email", email);
		query.setParameter("eid", eid);
		
		Employee employee = (Employee) query.uniqueResult();
	
			return employee;
	}
	

	public Employee DeletebyId(int id) {
		
		Session session = sf.openSession();
		Employee employee = session.get(Employee.class, id);
		
		if(employee!=null) {
			session.beginTransaction();
			session.delete(employee);
			session.getTransaction().commit();;
			
			return employee;		
		}	
		return null;
	}
	
	public String DeleteAll() {
		
		Session session = sf.openSession();
		
		session.beginTransaction();
		String hql="delete from Employee";
		Query query = session.createQuery(hql);
		query.executeUpdate();
		session.getTransaction().commit();
	
		String msg="All records deleted successfully";
		return msg;	
	}

}
