package com.example.project.service;

import java.util.List;

import com.example.project.beans.model.NewsModel;

public interface CrawlingService {
	
	public List<String> crawlGoogleSearchTrendList() throws Exception;
	
	public List<NewsModel> crawlingNaverSearchNewsLink(String keyword) throws Exception;
	
}
