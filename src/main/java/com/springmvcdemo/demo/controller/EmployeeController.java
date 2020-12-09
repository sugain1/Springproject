package com.springmvcdemo.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.springmvcdemo.demo.model.Employee;
import com.springmvcdemo.demo.repository.EmployeeRepository;

@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository erepo;

	
	@GetMapping("/employee")
	public String employeeform(Model model, HttpSession session) {
		if (StringUtils.isEmpty(session.getAttribute("validuser")) ) {
			return "loginForm";
		}
		
		model.addAttribute("emodel",new Employee());
		
		
		return  "employeeform";
	}
	
	@PostMapping("/employee")
	public String saveEmployee(@ModelAttribute Employee emp, HttpSession session) {
		if (StringUtils.isEmpty(session.getAttribute("validuser")) ) {
			return "loginForm";
		}
		erepo.save(emp);
		
		return "redirect:employee";
	}
	@GetMapping ("/list")
	
	public String getEmployees(Model model, HttpSession session) {
		if (StringUtils.isEmpty(session.getAttribute("validuser")) ) {
			return "loginForm";
		}
		
		model.addAttribute("elist",erepo.findAll());
		
		
		return "Home";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") int id, Model model, HttpSession session) {
		if (StringUtils.isEmpty(session.getAttribute("validuser")) ) {
			return "loginForm";
		}
		
		erepo.deleteById(id);
		model.addAttribute("elist",erepo.findAll());
		
		return "Home";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable int id , Model model, HttpSession session) {
		if (StringUtils.isEmpty(session.getAttribute("validuser")) ) {
			return "loginForm";
		}
		
		model.addAttribute("emodel",erepo.getOne(id));
		
		return "editForm";
	}
	
	@PostMapping("/update")
	public String update(@ModelAttribute Employee emp, Model model, HttpSession session) {
		if (StringUtils.isEmpty(session.getAttribute("validuser")) ) {
			return "loginForm";
		}	
		erepo.save(emp);
		model.addAttribute("elist",erepo.findAll());
		
		return "Home";
	}
	@GetMapping ("/home")
	public String home (Model model, HttpSession session) {
		if (StringUtils.isEmpty(session.getAttribute("validuser")) ) {
			return "loginForm";
		}
		
		model.addAttribute("elist",erepo.findAll());
		return "Home";
	}
}
