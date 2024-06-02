package com.example.project.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.project.service.CrawlingService;

@Controller
@RequestMapping({"/", "", "/index"})
public class IndexController {
	
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CrawlingService crawlingService;
	
	@GetMapping("/")
	public String index() throws Exception{
		List<String> list = crawlingService.crawlGoogleSearchTrendList();
		return "index";
	}
}
