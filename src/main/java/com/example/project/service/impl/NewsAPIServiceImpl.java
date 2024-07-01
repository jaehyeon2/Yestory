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

import com.example.project.service.NewsAPIService;

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
		String apiURL = NAVER_API_ENDPOINT_URL+URLEncoder.encode(trend, "UTF-8");
		logger.info("apiURL = {}", apiURL);
        HttpURLConnection connection = null;
        String responseBody = null;
        try {
        	URL url = new URL(apiURL);
        	connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("X-Naver-Client-Id", NAVER_API_CLIENT_ID);
            connection.setRequestProperty("X-Naver-Client-Secret", NAVER_API_CLIENT_SECRET);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                responseBody = readBody(connection.getInputStream());
            } else { // 오류 발생
                responseBody = readBody(connection.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            connection.disconnect();
        }
        logger.info("responseBody = {}", responseBody);
        
		
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
