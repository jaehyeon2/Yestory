package com.example.project.service;

import com.example.project.beans.model.MNewsModel;
import com.example.project.beans.param.NewsParam;

public interface CrawlingService {
	
	public void crawlingNaverSearchNewsLink(String keyword) throws Exception;
	
	public MNewsModel crawlingNaverNews(NewsParam newsParam) throws Exception;
}
