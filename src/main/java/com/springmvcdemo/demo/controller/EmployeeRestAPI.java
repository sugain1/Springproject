package com.springmvcdemo.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.springmvcdemo.demo.model.Employee;
import com.springmvcdemo.demo.repository.EmployeeRepository;

@RestController
public class EmployeeRestAPI {
	
	@Autowired
	private EmployeeRepository  erepo;
	
	@GetMapping("/employee/list")
	public  List<Employee> getAllEmps() {
		
		return erepo.findAll();
	}
	
	@GetMapping("/employee/{id}")
	public  Employee getOneEmp(@PathVariable int id) {
		
		return  erepo.getOne(id);
		
	}
	@PostMapping("/employee/add")
public String addemployee(@RequestBody Employee emp) {
		erepo.save(emp);
	return"added success"; 
}
	@GetMapping ("/employee/delete/{id}")
	public String deleteemp (@PathVariable int id) {
		erepo.deleteById(id);
		return "delete success";
	}
	@GetMapping ("/employee/obj")
	public String json2object() {
	RestTemplate tmp = new RestTemplate();
		Employee emp = tmp.getForObject("http://localhost:9292/employee/10", Employee.class);
			return "result = "+ emp.toString();
	}
	
}
