package com.example.project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping(value = {"/", "", "/index"})
	public String adminIndex() throws Exception{
		
		
		
		return "admin/index";
		
	}

}
