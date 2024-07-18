package com.BikkadIT.SpringBootCurdWithRestfullHibernet.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.BikkadIT.SpringBootCurdWithRestfullHibernet.model.Employee;
import com.BikkadIT.SpringBootCurdWithRestfullHibernet.service.ServiceI;

@RestController
public class EmployeeController {

	@Autowired
	private ServiceI servicei;
	
	@PostMapping(value = "/saveEmployee", consumes="application/json",produces = "application/json")
	public ResponseEntity<String> saveEmployee(@RequestBody  Employee employee){

		Integer empId = servicei.saveEmployee(employee);
		
		String msg="Employee datails has been saved successfully with EmployeeId : "+empId;
		return new ResponseEntity<String>  (msg,HttpStatus.CREATED);

	}
	
	
	@PostMapping(value = "/saveAllEmployee", consumes="application/json",produces = "application/json")
	public ResponseEntity<String> saveAddEmployee(@RequestBody  List<Employee> employee){

		String employees = servicei.saveAllEmployees(employee);
		
		
		return new ResponseEntity<String>  (employees,HttpStatus.CREATED);

	}
	
	
	@GetMapping(value="/getById", consumes = "application/json", produces="application/json")
	public ResponseEntity<Employee> getById(@RequestBody Employee employee){
		
		Employee employee2 = servicei.getById(employee.getEmpId());
		
		if(employee2!=null) {
		return new ResponseEntity<Employee> (employee2, HttpStatus.OK);
		}

		return new ResponseEntity<Employee> (employee2, HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(value="/getAll", produces="application/json")
	public List<Employee> getAllEmployees(){
		
		List<Employee> employees = servicei.getAllEmployees();
	
		return employees;
		
		
	}
	
	
	@GetMapping(value="/findByAge", consumes = "application/json", produces="application/json")
	public  ResponseEntity<List<Employee>>  getAgeLessThan(@RequestBody Employee employee){
		System.out.println(employee.getEmpAge());
		List<Employee> list = servicei.getAgeLessThan(employee.getEmpAge());
		
		if(list!=null) {
		return new ResponseEntity<List<Employee>> (list, HttpStatus.OK);
		}
		return new ResponseEntity<List<Employee>> (list, HttpStatus.NO_CONTENT);
	}
	
	@PutMapping(value = "/update", consumes="application/json", produces = "application/json")
	public ResponseEntity<String>  update(@RequestBody   Employee employee){
		
		Employee employee2 = servicei.update(employee);
		
		if(employee2!=null) {
			String response="Employee with EmpId "+employee2.getEmpId()+ " has been Updated ";
			return new ResponseEntity<String>(response, HttpStatus.CREATED);
		}
		else{
		String response="Update failed";
		
		return new ResponseEntity<String>(response,HttpStatus.BAD_REQUEST);
		
		}
	
	}
	@PutMapping(value = "/updateAll", consumes="application/json", produces = "application/json")
	public  ResponseEntity<List<Employee>>   updateAll( @RequestBody    List<Employee> employees){
	
		List<Employee> list = servicei.updateAll(employees);
		
		if(list!=null) {
			return new ResponseEntity<List<Employee>>(list,HttpStatus.CREATED);
			
		}
	
		return new ResponseEntity<List<Employee>>(list,HttpStatus.BAD_REQUEST);
	
	}
	
	@GetMapping(value = "/login", consumes="application/json")
	public   ResponseEntity<String>  LoginCheck(@RequestBody Employee employee) {
		
		Employee employee2 = servicei.LoginCheck(employee.getEmpEmail(), employee.getEmpId());
		
		if(employee2!=null) {
			String response1="Loggedin succesfully";
			return new ResponseEntity<String>(response1,HttpStatus.OK);
			
		}else {
		String response="Login fail, invalid email or id";
	    return new ResponseEntity<String>(response,HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@DeleteMapping(value = "/deleteid", consumes="application/json", produces = "application/json")
	public  ResponseEntity< String>  DeletebyId (@RequestBody Employee employee) {
		
		Employee employee2 = servicei.DeletebyId(employee.getEmpId());
		
		if(employee2!=null) {
			String msg="Employee has been removed";
			return new ResponseEntity<String>(msg,HttpStatus.CREATED);
		
		}
		
		String msg="Employee is not available in database to delete";
		return new ResponseEntity<String>(msg,HttpStatus.NOT_FOUND);

	}
	
	@DeleteMapping(value="/deleteAll", produces = "application/json")
	public ResponseEntity< String> DeleteAll() {
		String msg = servicei.DeleteAll();
		
		return new ResponseEntity<String>  (msg,HttpStatus.CREATED);

	}

}
