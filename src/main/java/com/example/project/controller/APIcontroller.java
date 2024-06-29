package com.example.project.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.project.beans.enums.RequestType;
import com.example.project.beans.model.ResponseModel;
import com.example.project.beans.model.YSummaryModel;
import com.example.project.beans.param.RequestParam;
import com.example.project.beans.param.SummaryParam;
import com.example.project.service.ChatbotAPIService;
import com.example.project.service.SummaryYService;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/api/request")
public class APIcontroller {
	
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ChatbotAPIService chatbotAPIService;
	
	@Autowired
	private SummaryYService summaryYService;
	
	@PostMapping(value={"/test"})
	@ResponseBody
	public ResponseModel request(@RequestBody RequestParam requestParam) throws Exception{
		
		ResponseModel response = new ResponseModel();
		response = chatbotAPIService.getResponseOfText(requestParam, "simpleTextTest");
        
		return response;
	}
	
	@PostMapping(value={"/summary"})
	@ResponseBody
	public ResponseModel summaryRequest(@RequestBody RequestParam requestParam) throws Exception{
		
		ResponseModel response = chatbotAPIService.getResponseOfText(requestParam, "summaryTest");
        
		return response;
	}
	
	@PostMapping(value={"/trend"})
	@ResponseBody
	public ResponseModel trendRequest(@RequestBody RequestParam requestParam) throws Exception {
		
		ResponseModel response = chatbotAPIService.getResponseOfTrendList(requestParam);

        return response;
    }
	
	@GetMapping(value={"/summaryTest"})
	public void summaryTest(@Valid SummaryParam summaryParam) throws Exception{
		summaryParam.setMtTrend("소설가 정지돈");
		summaryParam.setNumber("1");
		
		YSummaryModel summary = summaryYService.selectSummary(summaryParam);
		logger.info("APIController::summaryTest::mtTrend = {}", summary.getMtTrend());
		logger.info("APIController::summaryTest::msTitle = {}", summary.getMsTitle());
		logger.info("APIController::summaryTest::msSummary = {}", summary.getMsSummary());
		
	}
	@PostMapping(value={"/summaryPost"})
	@ResponseBody
	public ResponseModel summaryPostRequest(@RequestBody RequestParam requestParam) throws Exception{
		
		Map<String, Object> paramsMap = requestParam.getAction().getParams();
		logger.info("trendParam = {}", paramsMap.get("trend").toString());
		logger.info("numberParam = {}", paramsMap.get("number").toString());
		
		ResponseModel response = new ResponseModel();
		
		SummaryParam summaryParam = new SummaryParam();
		summaryParam.setMtTrend(paramsMap.get("trend").toString());
		summaryParam.setNumber(paramsMap.get("number").toString());
		
		YSummaryModel summary = summaryYService.selectSummary(summaryParam);
		if (summary==null){
			response = chatbotAPIService.getResponseOfTrend(requestParam);
		}else{
			response = chatbotAPIService.getResponseOfText(requestParam, summary.getMsSummary());
		}
		
		return response;
	}
	
	//temp postMapping
	@PostMapping(value={"/request_test"})
	@ResponseBody
	public ResponseModel request_test(@RequestBody RequestParam requestParam) throws Exception{
		Map<String, Object> paramsMap = requestParam.getAction().getParams();
		
		String textParam = paramsMap.get("textParam").toString();
		logger.info("textParam = {}", textParam);
		
		ResponseModel response = null;
		if (textParam == null){
			logger.error("APIController::request_test::Error = textParam is not exist!");
			response = chatbotAPIService.getResponseOfText(requestParam, "오류가 발생했습니다. 다시 입력해주세요.");
		}
		
		RequestType requestType = chatbotAPIService.getRequestType(textParam);
		
		
		response = chatbotAPIService.getResponseOfText(requestParam, "request_test");
		return response;
	}
}
