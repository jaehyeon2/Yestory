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

import com.example.project.beans.model.YTrendModel;
import com.example.project.beans.param.TrendParam;
import com.example.project.dao.master.MTrendDao;
import com.example.project.dao.slave.STrendDao;
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
	public boolean saveGoogleSearchTrendList() throws Exception {
	    
		List<String> trendList = new ArrayList<>();
	    String history = this.getYesterdayDate();
	    
	    try {
	        
	        logger.info("CrawlingServiceImpl::crawlGoogleSearchTrendList::yesterday = {}", history);
	        
	        String filePath = new StringBuilder()
	            .append(GOOGLE_TREND_FILE_PATH)
	            .append(GOOGLE_TREND_FILE_NAME_HEAD)
	            .append(history)
	            .append(".csv")
	            .toString();
	        
	        if (!new File(filePath).exists()){
	        	logger.info("TrendYServiceImpl::getGoogleSearchTrendList::info = generate new csv file");
	        	this.generateTrendToCsv(filePath);
	        }
	        trendList = this.getTrendListFromCsv(filePath);
	        
	        this.insertTrendList(trendList);
	        
	    } catch (Exception e) {
	        logger.error("CrawlingServiceImpl::crawlGoogleSearchTrendList::error = {}", e.getMessage(), e);
	        throw e;
	    }
	    
	    return true;
	}
	
	@Override
	public boolean insertTrendList(List<String> trendList) throws Exception{
		try{
			TrendParam trendParam = new TrendParam();
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
	
	@Override
	public List<YTrendModel> selectTrendList(TrendParam trendParam) throws Exception{
		List<YTrendModel> trendList = new ArrayList<>();
		
		Map<String, Object> map = new HashMap<>();
		try{
			map.put("history", trendParam.getHistory());
			
			trendList = sDbDao.getMapper(STrendDao.class).selectTrendList(map);
			
		}catch(Exception e){
			logger.error("TrendYServiceImpl::selectTrendList::Error = {}", e.getMessage());
		}
		
		return trendList;
	}
	
	@Override
	public boolean deleteTrend(TrendParam trendParam) throws Exception{
		Map<String, Object> map = new HashMap<>();
		try{
			map.put("history", trendParam.getHistory());
			
			int intResult = mDbDao.getMapper(MTrendDao.class).deleteTrend(map);
			if (intResult<1){
				logger.info("TrendYServiceImpl::deleteTrend::trend is not exist. history = {}", trendParam.getHistory());
			}
		}catch(Exception e){
			logger.error("TrendYServiceImpl::deleteTrend::Error = {}", e.getMessage());
			throw e;
			
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
	        while ((line = br.readLine()) != null && lineCount<5) {
	            trendList.add(line);
	            lineCount++;
	        }
	    }
	    logger.info("trendListSize = {}", trendList.size());
	    
	    return trendList;
	}
	
	private void insertTrend(TrendParam trendParam) throws Exception{
		
		Map<String, Object> map = new HashMap<>();
		
		try{
			String history = this.getYesterdayDate();
			map.put("mtTrend", trendParam.getMtTrend());
			map.put("history", history);
			
			int intResult = mDbDao.getMapper(MTrendDao.class).insertTrend(map);
			
			if (intResult<1){
				throw new Error("insertTrend error");
			}
			
		}catch(Exception e){
			logger.error("TrendYServiceImpl::insertTrend::Error = {}", e.getMessage());
			throw e;
		}
	}
}
