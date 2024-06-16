package com.example.project.service;

import com.example.project.beans.param.YnewsParam;

public interface GptYService {

	public String getGPTResponse(YnewsParam newsParam) throws Exception;
	
}
