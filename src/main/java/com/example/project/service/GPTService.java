package com.example.project.service;

public interface GPTService {

	public String makePrompt(String content) throws Exception;
	
	public String receiveAnswer(String prompt) throws Exception;
	
}
