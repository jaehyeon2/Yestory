package com.example.project.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.beans.param.YtrendParam;
import com.example.project.dao.master.MtrendDao;
import com.example.project.service.BasicService;
import com.example.project.service.TrendYService;

@Service
public class TrendYServiceImpl extends BasicService implements TrendYService {

final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private String GOOGLE_TREND_FILE_PATH;
	
	@Autowired
	private String GOOGLE_TREND_FILE_NAME_HEAD;
	
	@Autowired
	private String GOOGLE_TREND_PYTHON_FILE_PATH;
	
	@Override
	public List<String> getGoogleSearchTrendList() throws Exception {
	    
		List<String> trendList = new ArrayList<>();
	    String yesterdayString = this.getYesterdayDate();
	    
	    try {
	        
	        logger.info("CrawlingServiceImpl::crawlGoogleSearchTrendList::yesterday = {}", yesterdayString);
	        
	        String filePath = new StringBuilder()
	            .append(GOOGLE_TREND_FILE_PATH)
	            .append(GOOGLE_TREND_FILE_NAME_HEAD)
	            .append(yesterdayString)
	            .append(".csv")
	            .toString();
	        
	        if (!new File(filePath).exists()){
	        	logger.info("TrendYServiceImpl::getGoogleSearchTrendList::info = generate new csv file");
	        	this.generateTrendToCsv(filePath);
	        }
	        trendList = this.getTrendListFromCsv(filePath);
	        
	    } catch (Exception e) {
	        logger.error("CrawlingServiceImpl::crawlGoogleSearchTrendList::error = {}", e.getMessage(), e);
	        throw e;
	    }
	    
	    return trendList;
	}
	
	@Override
	public boolean insertTrendList(List<String> trendList) throws Exception{
		try{
			YtrendParam trendParam = new YtrendParam();
			
			trendParam.setHistory(this.getYesterdayDate());
	        for(String trend:trendList){
	        	trendParam.setMtTrend(trend);
	        	this.insertTrend(trendParam);
	        }
		}catch(Exception e){
			logger.error("TrendYServiceImpl::insertTrendList::Error = {}", e.getMessage());
			return false;
		}
		return true;
        
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
	    
	    logger.info("CrawlingServiceImpl::generateTrendToCsv::filePath = {}", filePath);
	}

	private List<String> getTrendListFromCsv(String filePath) throws Exception {
	    List<String> trendList = new ArrayList<>();
	    
	    try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {
	        String line;
	        // skip initial line
	        line = br.readLine();
	        int lineCount=0;
	        // 5번째 트렌드까지만 리스트에 저장
	        while ((line = br.readLine()) != null || lineCount<5) {
	            trendList.add(line);
	            lineCount++;
	        }
	    }
	    
	    return trendList;
	}
	
	private void insertTrend(YtrendParam trendParam) throws Exception{
		
		Map<String, Object> map = new HashMap<>();
		
		try{
			String history = this.getYesterdayDate();
			map.put("mtTrend", trendParam.getMtTrend());
			map.put("history", history);
			
			int intResult = mDbDao.getMapper(MtrendDao.class).insertTrend(map);
			
			if (intResult<1){
				throw new Error("insertTrend error");
			}
			
		}catch(Exception e){
			logger.error("TrendYServiceImpl::insertTrend::Error = {}", e.getMessage());
			throw e;
		}
	}
}
