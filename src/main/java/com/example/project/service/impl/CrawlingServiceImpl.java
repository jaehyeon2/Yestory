package com.example.project.service.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
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
	private String NAVER_CLIENT_ID;
	
	@Autowired
	private String NAVER_CLIENT_SECRET;
	
	@Autowired
    private String NAVER_DATALAB_API_URL;
	
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
	        for (String keyword:trendList){
	        	List<NewsModel> newsListByKeyword = getNaverDatalabTrend(keyword);
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
	        // skip initial line
	        line = br.readLine();
	        while ((line = br.readLine()) != null) {
	            trendList.add(line);
	        }
	    }
	    
	    return trendList;
	}

	@Override
	public List<NewsModel> getNaverDatalabTrend(String keyword) throws Exception {
		
		List<NewsModel> newsList = new ArrayList<>();
		String yesterdayString = LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		String todayString = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		try {
            // Create URL object
            URL url = new URL(NAVER_DATALAB_API_URL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            
            // Set request method to POST
            con.setRequestMethod("POST");
            con.setRequestProperty("X-Naver-Client-Id", NAVER_CLIENT_ID);
            con.setRequestProperty("X-Naver-Client-Secret", NAVER_CLIENT_SECRET);
            con.setRequestProperty("Content-Type", "application/json");

            // Create JSON body
            JSONObject body = new JSONObject();
            body.put("startDate", yesterdayString);
            body.put("endDate", todayString);
            body.put("timeUnit", "date");
            JSONArray keywordGroups = new JSONArray();
            JSONObject keywordGroup = new JSONObject();
            keywordGroup.put("groupName", keyword);
            keywordGroup.put("keywords", new JSONArray().put(keyword));
            keywordGroups.put(keywordGroup);
            body.put("keywordGroups", keywordGroups);

            // Send POST request
            con.setDoOutput(true);
            OutputStream os = con.getOutputStream();
            os.write(body.toString().getBytes("UTF-8"));
            os.flush();
            os.close();

            // Read response
            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Print result
                System.out.println(response.toString());
            } else {
                System.out.println("Error: " + responseCode);
            }

        } catch (Exception e) {
            logger.error("CrawlingServiceImpl::getNaverDatalabTrend::Error = {}", e.getMessage());
        }
		
		
		return null;
	}
	
	

}
