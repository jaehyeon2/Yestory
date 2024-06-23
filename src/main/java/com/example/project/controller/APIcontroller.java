package com.example.project.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.project.beans.model.ResponseModel;
import com.example.project.beans.model.YNewsModel;
import com.example.project.beans.param.RequestParam;
import com.example.project.beans.param.request.Action;

@Controller
@RequestMapping("/api")
public class APIcontroller {
	
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private String KAKAO_CHATBOT_SKILL_VERSION;
	
	@RequestMapping(value={"/request"}, headers="Accept=application/json", method=RequestMethod.POST)
	@ResponseBody
	public ResponseModel test(@RequestBody RequestParam requestParam) throws Exception{
		
		Map<String, Object> paramsMap = requestParam.getAction().getParams();
		
		logger.info(paramsMap.toString());
		logger.info("param1 = {}", paramsMap.get("param1"));
		logger.info("param2 = {}", paramsMap.get("param2"));
		
		ResponseModel response = new ResponseModel();
		
		Map<String, String> text= new HashMap<>();
		Map<String, Object> simpleText = new HashMap<>();
		text.put("text", "test_text");
		simpleText.put("simpleText", text);
		
		Map<String, Object> outputs = new HashMap<>();
		
		outputs.put("outputs", simpleText);
		
		
		response.setVersion(KAKAO_CHATBOT_SKILL_VERSION);
		response.setTemplate(outputs);
		
		System.out.println("response = " + response);
		return response;
	}
}
