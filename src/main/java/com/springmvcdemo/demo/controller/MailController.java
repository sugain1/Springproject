package com.springmvcdemo.demo.controller;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;import javax.servlet.http.HttpServletRequest;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MailController {
	 @Autowired
	    private JavaMailSender javaMailSender;
	@GetMapping("/email") 
	 public String getemailform() {
		 return "email";
	 }
	 
	 @PostMapping ("/email")
	 public String email(HttpServletRequest req ) throws MessagingException, IOException {
	    	String email = req.getParameter("email");
	    	String subject = req.getParameter("subject");
	    	String message = req.getParameter("msg");
	    	//sendEmail(email, subject, message);
	    	sendEmailWithAttachment(email, subject, message);
	    	return "email";
	 }
	    void sendEmail(String toemail, String sub,String body) {

	        SimpleMailMessage msg = new SimpleMailMessage();
	        msg.setTo(toemail);//("to_1@gmail.com", "to_2@gmail.com", "to_3@yahoo.com");
            msg.setSubject(sub);
	        msg.setText(body);

	        javaMailSender.send(msg);

	    }
	    
	    void sendEmailWithAttachment(String toemail, String sub,String body) throws MessagingException, IOException {

	        MimeMessage msg = javaMailSender.createMimeMessage();

	        // true = multipart message
	        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
	        
	        helper.setTo(toemail);

	        helper.setSubject(sub);

	        // default = text/plain
	        //helper.setText("Check attachment for image!");

	        // true = text/html
	        helper.setText("<h1>Check attachment for image!</h1>", true);

	        // hard coded a file path
	        //FileSystemResource file = new FileSystemResource(new File("path/android.png"));

	        helper.addAttachment("my_photo.png", new ClassPathResource("static/imgs/002.jpg"));

	        javaMailSender.send(msg);

	    }
}
