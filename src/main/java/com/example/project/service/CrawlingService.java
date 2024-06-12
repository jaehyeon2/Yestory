package com.example.project.service;

import com.example.project.beans.model.NewsModel;
import com.example.project.beans.param.NewsParam;

public interface CrawlingService {
	
	public void crawlingNaverSearchNewsLink(String keyword) throws Exception;
	
	public NewsModel crawlingNaverNews(NewsParam newsParam) throws Exception;
}
