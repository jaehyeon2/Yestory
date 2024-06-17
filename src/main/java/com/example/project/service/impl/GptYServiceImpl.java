package com.example.project.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.beans.param.YnewsParam;
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
	private String OPENAI_API_GPT_MODEL;
	
	@Autowired
	private String HEAD_PROMPT;
	
	@Autowired
	private String BODY_PROMPT;
	
	@Autowired
	private String TAIL_PROMPT;
	
	@Override
	public String getGPTResponse(YnewsParam newsParam) throws Exception {
        
		String fullPrompt = this.makePrompt(newsParam);
		
        String requestBody = createRequestBody(fullPrompt);

        // OpenAI API 연결 설정
        URL url = new URL(OPENAI_API_ENDPOINT_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Authorization", "Bearer " + OPENAI_API_KEY);
        connection.setRequestProperty("Content-Type", "application/json; utf-8");
        connection.setDoOutput(true);
        
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = requestBody.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        // 응답 정상 여부 확인
        int responseCode = connection.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new IOException("Unexpected code " + responseCode);
        }

        // 응답 받아오기
        StringBuilder response = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
        }
        
        String gptAnswer = parseResponse(response.toString());
        return gptAnswer;
    }
	
	private String makePrompt(YnewsParam newsParam){
		
		String fullPrompt = new StringBuilder()
				.append(HEAD_PROMPT)
				.append(newsParam.getMnTitle())
				.append(BODY_PROMPT)
				.append(newsParam.getMnContent())
				.append(TAIL_PROMPT)
				.toString();
		
		return fullPrompt;
	}
	
    private String createRequestBody(String prompt) {
    	
        JSONObject json = new JSONObject();
        json.put("model", OPENAI_API_GPT_MODEL);

        JSONArray messages = new JSONArray();
        JSONObject message = new JSONObject();
        message.put("role", "user");
        message.put("content", prompt);
        messages.put(message);

        json.put("messages", messages);

        return json.toString();
    }

    //responseBody에서 gptAnswer 추출
    private String parseResponse(String responseBody) {
        JSONObject json = new JSONObject(responseBody);
        JSONArray choices = json.getJSONArray("choices");
        JSONObject message = choices.getJSONObject(0).getJSONObject("message");
        return message.getString("content");
    }
}
