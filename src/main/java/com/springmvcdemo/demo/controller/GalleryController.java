package com.springmvcdemo.demo.controller;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class GalleryController {

	@GetMapping ("/gallery")
	public String gallery (Model model) {
	 String[] imglist = new File("C:\\Users\\Sugain\\Documents\\workspace-spring-tool-suite-4-4.8.0.RELEASE\\springmvcdemo\\src\\main\\resources\\static\\imgs").list();   // Arrays.asList("002.jpg","003.jpg","004.jpg","005.jpg","006.jpg","007.jpg","008.jpg","009.jpg","01.jpg","02.jpg","03.jpg","04.jpg");
	model.addAttribute("imglist",imglist);
	return "galleryform";
}
	
}
