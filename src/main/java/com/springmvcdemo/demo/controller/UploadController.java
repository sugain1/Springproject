package com.springmvcdemo.demo.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {
@GetMapping ("/upload")
	public String uploadform () {
		return "UploadForm";
	}
@PostMapping ("/upload")	
public String savefile (@RequestParam("file")MultipartFile file , Model model) throws IOException {
	if (!file.isEmpty()) {
		FileOutputStream out = new FileOutputStream("C:\\Users\\Sugain\\Documents\\workspace-spring-tool-suite-4-4.8.0.RELEASE\\springmvcdemo\\src\\main\\resources\\static\\imgs\\"+ file.getOriginalFilename());
	out.write(file.getBytes());
	out.close();
	model.addAttribute("msg","upload success");
	
	}
	
	return "UploadForm";
}
}

