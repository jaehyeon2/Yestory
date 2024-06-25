package com.example.project.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.beans.model.ResponseModel;
import com.example.project.beans.model.YTrendModel;
import com.example.project.beans.model.response.Template;
import com.example.project.beans.model.response.template.output.Carousel;
import com.example.project.beans.model.response.template.output.SimpleText;
import com.example.project.beans.model.response.template.output.carousel.Item;
import com.example.project.beans.model.response.template.outputType.OutputBasicCard;
import com.example.project.beans.model.response.template.outputType.OutputText;
import com.example.project.beans.param.RequestParam;
import com.example.project.beans.param.TrendParam;
import com.example.project.service.BasicService;
import com.example.project.service.ChatbotAPIService;
import com.example.project.service.TrendYService;

@Service
public class ChatbotAPIServiceImpl extends BasicService implements ChatbotAPIService{

	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private String KAKAO_CHATBOT_SKILL_VERSION;
	
	@Autowired
	private TrendYService trendYService;
	
	@Override
	public ResponseModel getResponseOfTrend(RequestParam requestParam) throws Exception {

        // ResponseModel 객체 생성
        ResponseModel response = new ResponseModel();
        try{
	        
	        
	        TrendParam trendParam = new TrendParam();
	        
	        trendParam.setHistory(this.getYesterdayDate());
	        
	        List<YTrendModel> trendList = trendYService.selectTrendList(trendParam);
	
	        List<Item> items = new ArrayList<>();
	        
	        for (YTrendModel trend:trendList){
	        	Item item = new Item();
	        	item.setTitle("trend");
	        	item.setDescription(trend.getMtTrend());
	        	items.add(item);
	        }
	
	        Carousel carousel = new Carousel();
	        carousel.setType("basicCard");
	        carousel.setItems(items);
	
	        // Output 객체 생성 및 설정
	        OutputBasicCard output = new OutputBasicCard();
	        output.setCarousel(carousel);
	
	        // Template 객체 생성 및 Output 설정
	        Template template = new Template();
	        template.setOutputs(Collections.singletonList(output));
	
	        // ResponseModel 설정
	        response.setVersion(KAKAO_CHATBOT_SKILL_VERSION);
	        response.setTemplate(template);
        }catch(Exception e){
        	logger.error("ChatbotAPIServiceImpl::getResponseOfTrend::Error = {}", e.getMessage());
        	response = this.getErrorMessage();
        }
		return response;
	}

	@Override
	public ResponseModel getResponseOfText(RequestParam requestParam) throws Exception {

        // ResponseModel 객체 생성
        ResponseModel response = new ResponseModel();
        try{
	        
        	Map<String, Object> paramsMap = requestParam.getAction().getParams();
    		logger.info("paramsMap = ", paramsMap.toString());
    		String responseText = "안녕하세요! 이것은 챗봇의 응답입니다.";

            // SimpleText 객체 생성 및 설정
            SimpleText simpleText = new SimpleText();
            simpleText.setText(responseText);

            // Output 객체 생성 및 SimpleText 설정
            OutputText output = new OutputText();
            output.setSimpleText(simpleText);

            // Template 객체 생성 및 Output 설정
            Template template = new Template();
            template.setOutputs(Collections.singletonList(output));

            // ResponseModel 설정
            response.setVersion(KAKAO_CHATBOT_SKILL_VERSION);
            response.setTemplate(template);
        }catch(Exception e){
        	logger.error("ChatbotAPIServiceImpl::getResponseOfTrend::Error = {}", e.getMessage());
        	response = this.getErrorMessage();
        }
		return response;
	}
	
	private ResponseModel getErrorMessage() throws Exception{
		String responseText = "오류가 발생했습니다.\n 지속적으로 오류가 발생할 경우 문의 바랍니다.";

        // ResponseModel 객체 생성
        ResponseModel response = new ResponseModel();

        // SimpleText 객체 생성 및 설정
        SimpleText simpleText = new SimpleText();
        simpleText.setText(responseText);

        // Output 객체 생성 및 SimpleText 설정
        OutputText output = new OutputText();
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
