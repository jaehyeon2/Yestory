package com.example.project.service.impl;

import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.service.CrawlingService;

@Service
public class CrawlingServiceImpl implements CrawlingService{
	
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private String GOOGLE_TREND_URL;
	
	@Autowired
	private String GOOGLE_TREND_FILE_PATH;
	
	@Autowired
	private String GOOGLE_TREND_FILE_NAME_HEAD;
	
	@Override
	public List<String> crawlGoogleSearchTrendList() throws Exception{
		
		List<String> trendList = new ArrayList<>();
		logger.info("GOOGLE_TREND_URL = {}", GOOGLE_TREND_URL);
		logger.info("GOOGLE_TREND_FILE_PATH = {}", GOOGLE_TREND_FILE_PATH);
		logger.info("GOOGLE_TREND_FILE_NAME_HEAD = {}", GOOGLE_TREND_FILE_NAME_HEAD);
		
		try{
			String yesterdayString = LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
			logger.info("yesterday = {}", yesterdayString);
			
			StringBuilder filePath = new StringBuilder();
			filePath
				.append(GOOGLE_TREND_FILE_PATH)
				.append(GOOGLE_TREND_FILE_NAME_HEAD)
				.append(yesterdayString)
				.append(".csv");
			
//			ProcessBuilder pb = new ProcessBuilder(/python_function, pythonScript, filaPath);
//            Process process = pb.start();
			
			logger.info("filePath = {}", filePath.toString());
			
		}catch(Exception e){
			logger.error("error = {}", e.getMessage());
		}
		
		return trendList;
		
	}
	

}
