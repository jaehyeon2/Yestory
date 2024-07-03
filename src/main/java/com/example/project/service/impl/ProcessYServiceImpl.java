package com.example.project.service.impl;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import com.example.project.beans.enums.NewsType;
import com.example.project.beans.model.YNewsModel;
import com.example.project.beans.model.YTrendModel;
import com.example.project.beans.param.NewsParam;
import com.example.project.beans.param.SummaryParam;
import com.example.project.beans.param.TrendParam;
import com.example.project.service.BasicService;
import com.example.project.service.CrawlingYService;
import com.example.project.service.GptYService;
import com.example.project.service.NewsYService;
import com.example.project.service.ProcessYService;
import com.example.project.service.SummaryYService;
import com.example.project.service.TrendYService;

@EnableAsync
@Service
public class ProcessYServiceImpl extends BasicService implements ProcessYService {
	
	final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TrendYService trendYService;
	
	@Autowired
	private NewsYService newsYService;
	
	@Autowired
	private CrawlingYService crawlingYService;
	
	@Autowired
	private GptYService gptYService;
	
	@Autowired
	private SummaryYService summaryYService;
	
	@Override
	public void executeProcess() throws Exception{
		String history=this.getYesterdayDate();
		
		try{
			TrendParam trendParam = new TrendParam();
			trendParam.setHistory(history);
			
//			initial trend history
	        trendYService.deleteTrend(trendParam);
//	        save new trend list
			trendYService.saveGoogleSearchTrendList();
			
			List<YTrendModel> trendList = trendYService.selectTrendList(trendParam);
			
			NewsParam newsParam = new NewsParam();
			newsParam.setHistory(history);
//			initial news history
			newsYService.deleteNews(newsParam);
//			save new news list
			for (YTrendModel trend:trendList){
				logger.info("trend = {}", trend);
				crawlingYService.crawlingNaverNewsList(trend.getMtTrend());
			}
			
			List<YNewsModel> newsList = newsYService.selectNewsList(newsParam);
			
			SummaryParam summaryParam = new SummaryParam();
			summaryParam.setHistory(history);
//			initial summary history
			summaryYService.deleteSummary(summaryParam);
//			save new summary list
			for (YNewsModel news:newsList){
				if (news.getMnType().equals(NewsType.COMMON.getTypeName())){
					newsParam.setHistory(history);
					newsParam.setMnTitle(news.getMnTitle());
					newsParam.setMnContent(news.getMnContent());
					
					summaryParam.setMsSummary(gptYService.getGPTResponse(newsParam));					
				}else{
					summaryParam.setMsSummary(news.getMnContent());
				}
				summaryParam.setMtTrend(news.getMtTrend());
				summaryParam.setMsTitle(news.getMnTitle());
				summaryParam.setMsUrl(news.getMnUrl());
				
				summaryYService.insertSummary(summaryParam);
			}
			logger.info("ProcessYServiceImpl::executeProcess::info = serial process is successfully finish");
			
		}catch(Exception e){
			logger.error("ProcessYServiceImpl::executeProcess::Error = {}", e.getMessage());
		}
		
	}

}
