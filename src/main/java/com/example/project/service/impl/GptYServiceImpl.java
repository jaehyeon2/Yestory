package com.example.project.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.service.BasicService;
import com.example.project.service.GptYService;


@Service
public class GptYServiceImpl extends BasicService implements GptYService{
	
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private String OPENAI_API_KEY;
	
	@Autowired
	private String OPENAI_API_ENDPOINT_URL;
	
	@Autowired
	private String HEAD_PROMPT;
	
	@Autowired
	private String BODY_PROMPT;
	
	@Autowired
	private String TAIL_PROMPT;
	
	@Override
	public String receiveAnswer(String prompt) throws Exception {
        StringBuilder response = new StringBuilder();

        String fullPrompt = this.makePrompt(prompt);
        try {
        	
        	logger.info("receiveAnswer = {}", fullPrompt);
        	
            // 생성할 텍스트 및 요청 데이터 설정
            String requestData = new JSONObject()
            		.put("model", "gpt-3.5-turbo")
                    .put("prompt", fullPrompt)
                    .put("max_tokens", 5) // Optional: you can set other parameters as needed
                    .toString();

            // URL 및 연결 생성
            URL url = new URL(OPENAI_API_ENDPOINT_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // HTTP 메서드 설정
            connection.setRequestMethod("POST");

            // HTTP 요청 헤더 설정
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            connection.setRequestProperty("Authorization", "Bearer " + OPENAI_API_KEY);

            // 요청 본문 작성
            connection.setDoOutput(true);
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = requestData.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // HTTP 응답 처리
            int status = connection.getResponseCode();
            if (status == HttpURLConnection.HTTP_OK) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line.trim());
                    }
                }
            } else {
                logger.error("Error: Received HTTP status code {}", status);
                throw new IOException("Failed to receive a valid response from the API");
            }

            // JSON 응답 파싱
            JSONObject jsonResponse = new JSONObject(response.toString());
            String result = jsonResponse.getJSONArray("choices").getJSONObject(0).getString("text").trim();

            return result;

        } catch (Exception e) {
            logger.error("GPTServiceImpl::receiveAnswer::Error = {}", e.toString());
            throw e;
        }
    }
	
	private String makePrompt(String prompt){
		
		String totalPrompt = new StringBuilder()
				.append(HEAD_PROMPT)
				.append("title")
				.append(BODY_PROMPT)
				.append("content")
				.append(TAIL_PROMPT)
				.toString();
		
		return totalPrompt;
	}
	
}
