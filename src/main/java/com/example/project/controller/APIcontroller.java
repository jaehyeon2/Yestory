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
		
		logger.info("APIController::request::info = test");
		String responseText = "안녕하세요! 이것은 챗봇의 응답입니다.";

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
	
	@PostMapping(value={"/summary"})
	@ResponseBody
	public ResponseModel summaryRequest(@RequestBody RequestParam requestParam) throws Exception{
		
		Map<String, Object> paramsMap = requestParam.getAction().getParams();
		logger.info("paramsMap = ", paramsMap.toString());
		String responseText = "안녕하세요! 이것은 챗봇의 응답입니다.2";

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
	
	@PostMapping(value={"/trend"})
	@ResponseBody
	public ResponseModel trendRequest(@RequestBody RequestParam requestParam) throws Exception {
        Map<String, Object> paramsMap = requestParam.getAction().getParams();

        String responseText = "trend!";
        logger.info("trend!!!");

        // ResponseModel 객체 생성
        ResponseModel response = new ResponseModel();

        // SimpleText 객체 생성 및 설정
        SimpleText simpleText = new SimpleText();
        simpleText.setText(responseText);

        // Carousel 및 Item 객체 생성
        Item item1 = new Item();
        Item item2 = new Item();

        item1.setTitle("title1");
        item1.setDescription("description1");

        item2.setTitle("title2");
        item2.setDescription("description2");

        List<Item> items = new ArrayList<>();
        items.add(item1);
        items.add(item2);

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

        return response;
    }
}
