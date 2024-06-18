package com.example.project.service;

import com.example.project.beans.param.NewsParam;

public interface GptYService {

	public String getGPTResponse(NewsParam newsParam) throws Exception;
	
}
