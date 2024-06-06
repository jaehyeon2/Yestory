package com.example.project.service.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.beans.model.NewsModel;
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
	
	@Autowired
	private String KAKAO_API_KEY;
	
	@Autowired
	private String KAKAO_API_ENDPOINT_URL;
	
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
//	        for (String keyword:trendList){
//	        	List<NewsModel> newsListByKeyword = crawlingNaverSearchNewsLink(keyword);
//	        }
	        List<NewsModel> newsListByKeyword = crawlingNaverSearchNewsLink(trendList.get(0));
	        
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
	    logger.info("CrawlingServiceImpl::generateTrendToCsv::filePath = {}", filePath);
	}

	private List<String> getTrendListFromCsv(String filePath) throws Exception {
	    List<String> trendList = new ArrayList<>();
	    
	    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
	        String line;
	        // skip initial line
	        line = br.readLine();
	        while ((line = br.readLine()) != null) {
	            trendList.add(line);
	        }
	    }
	    
	    return trendList;
	}

	//TODO:
	@Override
	public List<NewsModel> crawlingNaverSearchNewsLink(String keyword) throws Exception {

		List<NewsModel> newsList = new ArrayList<>();
		String yesterdayString = LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
		String todayString = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
		try {
            
			// 키워드 인코딩
			String encodedQuery = URLEncoder.encode(keyword, "UTF-8");
			// 완전한 URL 생성
			String fullUrl = KAKAO_API_ENDPOINT_URL + "?query=" + encodedQuery;
            
            
			// URL 객체 생성
			URL url = new URL(fullUrl);
			
			// HttpURLConnection 객체 생성 및 설정
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Authorization", "KakaoAK " + KAKAO_API_KEY);
			
			int responseCode = conn.getResponseCode();
			System.out.println("Response Code: " + responseCode);
			
			// 응답 읽기
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuilder response = new StringBuilder();
			
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
			// 응답 출력
			System.out.println("Response: " + response.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	

}
