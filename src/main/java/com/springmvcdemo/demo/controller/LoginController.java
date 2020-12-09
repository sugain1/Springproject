package com.springmvcdemo.demo.controller;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springmvcdemo.demo.model.User;
import com.springmvcdemo.demo.model.VerifyRecaptcha;
import com.springmvcdemo.demo.repository.EmployeeRepository;
import com.springmvcdemo.demo.repository.UserRepository;

@Controller
public class LoginController {

	  @Autowired
	  private UserRepository  urepo;
	  
	  @Autowired
	  private EmployeeRepository erepo;
	
 @RequestMapping(value = "/userlogin", method=RequestMethod.GET)
  public String loginForm() {
	  
	  return "loginForm";
  }
 
 @RequestMapping(value = "/userlogin", method=RequestMethod.POST)
 public String checkUser(@ModelAttribute User u, Model model,HttpSession session,HttpServletRequest request ) throws IOException {
 String input = request.getParameter("g-recaptcha-response");
 boolean result = VerifyRecaptcha.verify(input); 
 if(result) {
 
	 u.setPassword(DigestUtils.md5DigestAsHex(u.getPassword().getBytes())); 
 User usr = urepo.findByUsernameAndPassword(u.getUsername(), u.getPassword());
	   
 if(usr!=null) {
		   model.addAttribute("un",u.getUsername());
		   
		   model.addAttribute("elist",erepo.findAll());
		   session.setAttribute("validuser", u.getUsername());
		   session.setMaxInactiveInterval(3*60);
		   session.setAttribute("user", usr);
		   
		   return  "home";
	   } else {
		   model.addAttribute("msg","user not found!!");
			 return "loginForm";
	   }
	   
 }
	 model.addAttribute("msg","You are not human!!");
	 return "loginForm";
 }
@GetMapping ("/logout")
 public String logout(HttpSession session) {
 session.invalidate();
 
	return "loginForm";
	}
@GetMapping ("/profile")
public String profile () {
	return "profile";
}
@RequestMapping(value = "/facebook", method = RequestMethod.GET)
public String fbLogin(){
	
	//String secret_key = "c85c3bbaeded9ce1ea6af891cc8733c2";
	//String app_id = "1439123129660532";
	
	return "redirect:https://www.facebook.com/dialog/oauth?client_id=1574781862725384&redirect_uri=http://localhost:9090/authorize/facebook&response_type=code&scope=email";
}

@RequestMapping(value = "/authorize/facebook", method = RequestMethod.GET)
public String saveFbUser(String code, Model model, HttpServletRequest request){
	
	model.addAttribute("elist", erepo.findAll());
	
      return "Home";
      
      
      
      }

}
