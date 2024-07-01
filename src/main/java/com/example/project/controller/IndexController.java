package com.example.project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.project.service.NewsAPIService;
import com.example.project.service.ProcessYService;

@Controller
@RequestMapping({"/", "", "/index"})
public class IndexController {
	
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ProcessYService processYService;
	
	@Autowired
	private NewsAPIService newsAPIservice;
	
	@GetMapping("/")
	public String index() throws Exception{
		return "index";
	}
	
	@GetMapping("/process")
	public String process() throws Exception{
		processYService.executeProcess();
		return "index";
	}
	
	@GetMapping("/gpt")
	public String gptTest() throws Exception{
		logger.info("gptTest");
		return "index";
		
	}
	
	@GetMapping("/naver")
	public String naverTest() throws Exception{
		newsAPIservice.test("이순재");
		return "index";
	}
}
