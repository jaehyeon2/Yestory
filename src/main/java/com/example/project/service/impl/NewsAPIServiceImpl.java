package com.example.project.service.impl;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.beans.model.NaverNewsResponse;
import com.example.project.beans.model.newsResponse.News;
import com.example.project.service.NewsAPIService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class NewsAPIServiceImpl implements NewsAPIService{
	
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private String NAVER_API_CLIENT_ID;
	
	@Autowired
	private String NAVER_API_CLIENT_SECRET;
	
	@Autowired
	private String NAVER_API_ENDPOINT_URL;
	
	@Override
	public void test(String trend) throws Exception{
		String apiURL = NAVER_API_ENDPOINT_URL+URLEncoder.encode(trend, "UTF-8")+"&startDate=20230101&endDate=20230131";
		logger.info("apiURL = {}", apiURL);
        HttpURLConnection connection = null;
        StringBuffer responseBody = new StringBuffer();
        BufferedReader br = null;
        try {
        	URL url = new URL(apiURL);
        	connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("X-Naver-Client-Id", NAVER_API_CLIENT_ID);
            connection.setRequestProperty("X-Naver-Client-Secret", NAVER_API_CLIENT_SECRET);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
            	br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            } else { // 오류 발생
            	br = new BufferedReader(new InputStreamReader(connection.getErrorStream(), "UTF-8"));
            }
            String inputLine;
            
            while ((inputLine = br.readLine()) != null) {
                responseBody.append(inputLine);
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
        	br.close();
            connection.disconnect();
        }
        
        ObjectMapper mapper = new ObjectMapper();
        NaverNewsResponse newsResponse = mapper.readValue(responseBody.toString(), NaverNewsResponse.class);
        logger.info("newsList");
        for (News news : newsResponse.getItems()) {
            System.out.println("Title: " + news.getTitle());
            System.out.println("Link: " + news.getLink());
            System.out.println("Description: " + news.getDescription());
            System.out.println("PubDate: " + news.getPubDate());
            System.out.println();
        }
        
		
	}
	
	private static String readBody(InputStream body){
		InputStreamReader streamReader = new InputStreamReader(body);
		
        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();


            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }


            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는 데 실패했습니다.", e);
        }
    }
}
