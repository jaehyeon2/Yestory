package com.example.project.service.impl;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import com.example.project.service.BasicService;
import com.example.project.service.CrawlingYService;
import com.example.project.service.ProcessYService;
import com.example.project.service.TrendYService;

@EnableAsync
@Service
public class ProcessYServiceImpl extends BasicService implements ProcessYService {
	
	final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TrendYService trendYService;
	
	@Autowired
	private CrawlingYService crawlingYService;
	
	
	
	@Override
	public void executeProcess() throws Exception{
		
		List<String> trendList = trendYService.getGoogleSearchTrendList();
		
		
		for (String trend:trendList){
			logger.info("trend = {}", trend);
			crawlingYService.crawlingNaverSearchNews(trend);
			break;
		}
		
	}

}
