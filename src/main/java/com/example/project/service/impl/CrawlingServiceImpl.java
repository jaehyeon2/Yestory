package com.example.project.service.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.service.CrawlingService;

@Service
public class CrawlingServiceImpl implements CrawlingService{
	
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private String GOOGLE_TREND_FILE_PATH;
	
	@Autowired
	private String GOOGLE_TREND_FILE_NAME_HEAD;
	
	@Autowired
	private String GOOGLE_TREND_PYTHON_FILE_PATH;
	
	@Override
	public List<String> crawlGoogleSearchTrendList() throws Exception {
	    List<String> trendList = new ArrayList<>();
	    
	    try {
	        String yesterdayString = LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
	        logger.info("CrawlingServiceImpl::crawlGoogleSearchTrendList::yesterday = {}", yesterdayString);
	        
	        StringBuilder sbFilePath = new StringBuilder();
	        String filePath = sbFilePath
	            .append(GOOGLE_TREND_FILE_PATH)
	            .append(GOOGLE_TREND_FILE_NAME_HEAD)
	            .append(yesterdayString)
	            .append(".csv")
	            .toString();
	        
	        this.generateTrendToCsv(filePath);
	        trendList = this.getTrendListFromCsv(filePath);
	        for (String trend:trendList){
	        	logger.info("trend = {}", trend);
	        }
	        
	    } catch (Exception e) {
	        logger.error("CrawlingServiceImpl::crawlGoogleSearchTrendList::error = {}", e.getMessage(), e);
	    }
	    
	    return trendList;
	}

	private void generateTrendToCsv(String filePath) throws Exception {
	    ProcessBuilder pb = new ProcessBuilder("python", GOOGLE_TREND_PYTHON_FILE_PATH, filePath);
	    Process process = pb.start();
	    
	    // Python script error message check
	    try (BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {
	        StringBuilder errorMessage = new StringBuilder();
	        String errorLine;
	        while ((errorLine = errorReader.readLine()) != null) {
	            errorMessage.append(errorLine).append("\n");
	        }
	        
	        // exit process
	        int exitCode = process.waitFor();
	        if (exitCode != 0) {
	            // Case: process is fail
	            throw new RuntimeException("Python Script Execution Failed with error: " + errorMessage.toString());
	        }
	    }
	}

	private List<String> getTrendListFromCsv(String filePath) throws Exception {
	    List<String> trendList = new ArrayList<>();
	    
	    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
	        String line;
	        // initial line skip
	        line = br.readLine();
	        while ((line = br.readLine()) != null) {
	            trendList.add(line);
	        }
	    }
	    
	    return trendList;
	}

}
