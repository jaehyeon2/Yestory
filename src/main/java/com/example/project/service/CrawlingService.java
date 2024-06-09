package com.example.project.service;

import java.util.List;

import com.example.project.beans.param.NewsParam;

public interface CrawlingService {
	
	public List<NewsParam> crawlingNaverSearchNewsLink(String keyword) throws Exception;
	
}
