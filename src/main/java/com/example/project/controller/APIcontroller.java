package com.example.project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.project.beans.model.ResponseModel;
import com.example.project.beans.param.RequestParam;
import com.example.project.service.ChatbotAPIService;

@Controller
@RequestMapping("/api/request")
public class APIcontroller {
	
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ChatbotAPIService chatbotAPIService;
	
	@PostMapping(value={"/trendList"})
	@ResponseBody
	public ResponseModel trendRequest(@RequestBody RequestParam requestParam) throws Exception {
		logger.info("APIController::trendRequest::info = API-trendList");
		ResponseModel response = chatbotAPIService.getResponseOfTrendList(requestParam);

        return response;
    }
	
	@PostMapping(value={"/summary"})
	@ResponseBody
	public ResponseModel summaryPostRequest(@RequestBody RequestParam requestParam) throws Exception{
		logger.info("APIController::summaryPostRequest::info = API-summary");
		ResponseModel response = chatbotAPIService.getResponseOfSummary(requestParam);
		
		return response;
	}
	
	//test textMessage Request
	@PostMapping(value={"/test"})
	@ResponseBody
	public ResponseModel testRequest(@RequestBody RequestParam requestParam) throws Exception{
		logger.info("APIController::testRequest::info = API-test");
		ResponseModel response = chatbotAPIService.getResponseOfText(requestParam, "simpleTextTest");
        
		return response;
	}
}
