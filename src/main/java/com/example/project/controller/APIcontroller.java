package com.example.project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.project.beans.model.YNewsModel;

@Controller
@RequestMapping("/api")
public class APIcontroller {
	
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value={"/request"}, headers="Accept=application/json")
	public YNewsModel test(@RequestBody String request) throws Exception{
		
		YNewsModel news = new YNewsModel();
		
		news.setMnTitle("title_test");
		news.setMnContent("content_test");
		return news;
	}
}
