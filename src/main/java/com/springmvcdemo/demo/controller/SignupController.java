package com.springmvcdemo.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springmvcdemo.demo.model.User;
import com.springmvcdemo.demo.repository.UserRepository;

@Controller
public class SignupController {
	
	@Autowired
	private UserRepository urepo; //UserServive us = new UserServiceImpl();

	@RequestMapping(value = "/userSignup", method = RequestMethod.GET)
	public String SignupForm() {
		
		return "Signupform";
	}
	
	@RequestMapping(value = "/userSignup", method = RequestMethod.POST)
	public String SignupUser(@ModelAttribute User u) {
		u.setPassword(DigestUtils.md5DigestAsHex(u.getPassword().getBytes()));
		urepo.save(u);
		
		return "loginForm";
	}
}
