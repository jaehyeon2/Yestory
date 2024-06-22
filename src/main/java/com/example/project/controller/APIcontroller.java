package com.example.project.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.project.beans.model.ResponseModel;
import com.example.project.beans.model.YNewsModel;

@Controller
@RequestMapping("/api")
public class APIcontroller {
	
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value={"/request"}, headers="Accept=application/json", method=RequestMethod.POST)
	public ResponseModel test(@RequestBody String request) throws Exception{
		logger.info("apiRequest");
		ResponseModel response = new ResponseModel();
		
		Map<String, String> text= new HashMap<>();
		Map<String, Object> simpleText = new HashMap<>();
		text.put("text", "test_text");
		simpleText.put("simpleText", text);
		
		Map<String, Object> outputs = new HashMap<>();
		
		outputs.put("outputs", simpleText);
		
		
		response.setVersion("1.0");
		response.setTemplate(outputs);
		
		System.out.println("response = " + response);
		return response;
	}
}
