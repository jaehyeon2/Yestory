package com.example.project.controller;

import java.util.ArrayList;
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
import com.example.project.beans.model.response.template.output.Carousel;
import com.example.project.beans.model.response.template.output.SimpleText;
import com.example.project.beans.model.response.template.output.carousel.Item;
import com.example.project.beans.model.response.template.outputType.OutputBasicCard;
import com.example.project.beans.model.response.template.outputType.OutputText;
import com.example.project.beans.param.RequestParam;
import com.example.project.beans.param.request.Action;
import com.example.project.service.ChatbotAPIService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/api/request")
public class APIcontroller {
	
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private String KAKAO_CHATBOT_SKILL_VERSION;
	
	@Autowired
	private ChatbotAPIService chatbotAPIService;
	
	@PostMapping(value={"/test"})
	@ResponseBody
	public ResponseModel request(@RequestBody RequestParam requestParam) throws Exception{
		
		ResponseModel response = new ResponseModel();
		response = chatbotAPIService.getResponseOfText(requestParam);
        
		return response;
	}
	
	@PostMapping(value={"/summary"})
	@ResponseBody
	public ResponseModel summaryRequest(@RequestBody RequestParam requestParam) throws Exception{
		
		ResponseModel response = new ResponseModel();
		response = chatbotAPIService.getResponseOfText(requestParam);
        
		return response;
	}
	
	@PostMapping(value={"/trend"})
	@ResponseBody
	public ResponseModel trendRequest(@RequestBody RequestParam requestParam) throws Exception {
		
		ResponseModel response = chatbotAPIService.getResponseOfTrend(requestParam);

        return response;
    }
}
