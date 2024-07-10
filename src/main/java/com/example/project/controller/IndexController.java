package com.example.project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.project.service.ProcessYService;

@Controller
@RequestMapping({"/", "", "/index"})
public class IndexController {
	
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ProcessYService processYService;
	
	@GetMapping("/")
	public String index() throws Exception{
		
		return "index";
		
	}
	
	@GetMapping("/process")
	public String process() throws Exception{
		
		processYService.executeProcess();
		
		return "index";
	}
}
