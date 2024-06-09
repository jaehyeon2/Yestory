package com.example.project.service.impl;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import com.example.project.service.CrawlingService;
import com.example.project.service.ProcessService;
import com.example.project.service.TrendService;

@EnableAsync
@Service
public class ProcessServiceImpl implements ProcessService {
	
	final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TrendService trendService;
	
	@Autowired
	private CrawlingService crawlingService;
	
	
	
	@Override
	public void executeProcess() throws Exception{
		
		List<String> trendList = trendService.getGoogleSearchTrendList();
		
		
		
		
	}

}
