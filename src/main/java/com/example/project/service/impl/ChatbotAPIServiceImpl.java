package com.example.project.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.beans.enums.RequestType;
import com.example.project.beans.model.ResponseModel;
import com.example.project.beans.model.YSummaryModel;
import com.example.project.beans.model.YTrendModel;
import com.example.project.beans.model.response.Template;
import com.example.project.beans.model.response.template.output.basicCard.Carousel;
import com.example.project.beans.model.response.template.output.simpleText.SimpleText;
import com.example.project.beans.model.response.template.output.basicCard.carousel.BasicItem;
import com.example.project.beans.model.response.template.outputType.OutputBasicCard;
import com.example.project.beans.model.response.template.outputType.OutputText;
import com.example.project.beans.param.RequestParam;
import com.example.project.beans.param.SummaryParam;
import com.example.project.beans.param.TrendParam;
import com.example.project.service.BasicService;
import com.example.project.service.ChatbotAPIService;
import com.example.project.service.SummaryYService;
import com.example.project.service.TrendYService;

@Service
public class ChatbotAPIServiceImpl extends BasicService implements ChatbotAPIService{

	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private String KAKAO_CHATBOT_SKILL_VERSION;
	
	@Autowired
	private TrendYService trendYService;
	
	@Autowired
	private SummaryYService summaryYService;
	
	@Override
	public ResponseModel getResponseOfText(RequestParam requestParam, String responseText) throws Exception {

        // ResponseModel 객체 생성
        ResponseModel response = new ResponseModel();
        try{
	        
        	Map<String, Object> paramsMap = requestParam.getAction().getParams();
    		logger.info("paramsMap = ", paramsMap.toString());

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
        	response = this.getResponseOfError("오류가 발생했습니다. 관리자에게 문의해주세요.");
        }
		return response;
	}
	
	@Override
	public ResponseModel getResponseOfTrendList(RequestParam requestParam) throws Exception {

        // ResponseModel 객체 생성
        ResponseModel response = new ResponseModel();
        try{
	        
        	List<BasicItem> items = new ArrayList<>();
        	
	        TrendParam trendParam = new TrendParam();
	        trendParam.setHistory(this.getYesterdayDate());
	        List<YTrendModel> trendList = trendYService.selectTrendList(trendParam);
	        
	        for (YTrendModel trend:trendList){
	        	BasicItem item = new BasicItem();
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
        	response = this.getResponseOfError("오류가 발생했습니다. 관리자에게 문의해주세요.");
        }
		return response;
	}
	
	@Override
	public ResponseModel getResponseOfSummary(RequestParam requestParam) throws Exception{
		
		Map<String, Object> paramsMap = requestParam.getAction().getParams();
		
		String summaryRequestStr = paramsMap.get("summaryParam").toString();
		
		if (!summaryRequestStr.contains("-")){
			logger.error("ChatbotAPIServiceImpl::getResponseOfSummary::Error = unexpectedRequest. summaryStr = {}", summaryRequestStr);
			return this.getResponseOfError("\"[트렌드]-[숫자]\"의 정확한 형식으로 입력해 주세요.\n하나의 트렌드 당 5개의 뉴스 요약이 제공됩니다.\n내용을 확인하려면 \'요약\'을 입력해주세요.");
		}
		
		String[] summaryParamList = summaryRequestStr.split("-"); 
		SummaryParam summaryParam = new SummaryParam();
		summaryParam.setMtTrend(summaryParamList[0].trim());
		summaryParam.setNumber(summaryParamList[1].trim());
		summaryParam.setHistory(this.getYesterdayDate());
		
		YSummaryModel summary = summaryYService.selectSummary(summaryParam);
		
		//조회된 결과가 없는 경우
		if (summary==null){
			logger.error("ChatbotAPIServiceImpl::getResponseOfSummary::Error = summary is not exist. summary = {}, number = {}",
					summaryParam.getMtTrend(), summaryParam.getNumber());
			
			StringBuilder trendListSb = new StringBuilder();
			
			TrendParam trendParam = new TrendParam();
			trendParam.setHistory(this.getYesterdayDate());
			
			List<YTrendModel> trendList = trendYService.selectTrendList(trendParam);
			
			//트렌드 리스트 String
			for (int index=1; index<=trendList.size(); index++){
				trendListSb.append(index)
					.append(": ")
					.append(trendList.get(index).getMtTrend())
					.append("\n");
			}
			
			return this.getResponseOfText(requestParam, "해당 내용이 존재하지 않습니다.\n어제의 트렌드 리스트는 아래와 같습니다.\n\n"+trendListSb.toString()+"\n내용을 확인하려면 \'요약\'을 입력해주세요.");
		}
		
		return this.getResponseOfText(requestParam, summary.getMsSummary());
		
	}
	
	public RequestType getRequestType(String requestText) throws Exception{
		RequestType requestType = RequestType.UNSPECIFIED;
		
		if (requestText==null || requestText.isEmpty() || requestText==""){
			requestType = RequestType.ERROR;
		}
		if (requestText.equals("트렌드")){
			requestType = RequestType.TREND;
		}else if (requestText.contains("-")){
			requestType = RequestType.TREND_DETAIL;
		}else{
			requestType = RequestType.UNKNOWN;
		}
		
		return requestType;
	}
	
	private ResponseModel getResponseOfError(String errorMessage) throws Exception{
		
		String responseText = errorMessage;

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
