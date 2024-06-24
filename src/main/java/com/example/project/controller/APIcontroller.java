package com.example.project.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.project.beans.model.ResponseModel;
import com.example.project.beans.model.YNewsModel;
import com.example.project.beans.model.response.Template;
import com.example.project.beans.model.response.template.Output;
import com.example.project.beans.model.response.template.output.SimpleText;
import com.example.project.beans.param.RequestParam;
import com.example.project.beans.param.request.Action;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/api/request")
public class APIcontroller {
	
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private String KAKAO_CHATBOT_SKILL_VERSION;
	
	@PostMapping(value={"/test"})
	@ResponseBody
	public ResponseModel request(@RequestBody RequestParam requestParam) throws Exception{
		
		Map<String, Object> paramsMap = requestParam.getAction().getParams();
		
		logger.info(requestParam.toString());
		
		String responseText = "안녕하세요! 이것은 챗봇의 응답입니다.";

        // ResponseModel 객체 생성
        ResponseModel response = new ResponseModel();

        // SimpleText 객체 생성 및 설정
        SimpleText simpleText = new SimpleText();
        simpleText.setText(responseText);

        // Output 객체 생성 및 SimpleText 설정
        Output output = new Output();
        output.setSimpleText(simpleText);

        // Template 객체 생성 및 Output 설정
        Template template = new Template();
        template.setOutputs(Collections.singletonList(output));

        // ResponseModel 설정
        response.setVersion(KAKAO_CHATBOT_SKILL_VERSION);
        response.setTemplate(template);
        
		return response;
	}
	
	@PostMapping(value={"/summary"})
	@ResponseBody
	public ResponseModel summaryRequest(@RequestBody RequestParam requestParam) throws Exception{
		
		Map<String, Object> paramsMap = requestParam.getAction().getParams();
		
		logger.info(paramsMap.toString());
		logger.info("param1 = {}", paramsMap.get("param1"));
		logger.info("param2 = {}", paramsMap.get("param2"));
		
		String responseText = "안녕하세요! 이것은 챗봇의 응답입니다.2";

        // ResponseModel 객체 생성
        ResponseModel response = new ResponseModel();

        // SimpleText 객체 생성 및 설정
        SimpleText simpleText = new SimpleText();
        simpleText.setText(responseText);

        // Output 객체 생성 및 SimpleText 설정
        Output output = new Output();
        output.setSimpleText(simpleText);

        // Template 객체 생성 및 Output 설정
        Template template = new Template();
        template.setOutputs(Collections.singletonList(output));

        // ResponseModel 설정
        response.setVersion(KAKAO_CHATBOT_SKILL_VERSION);
        response.setTemplate(template);
        
		return response;
	}
}
