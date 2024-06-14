package com.example.project.service;

import com.example.project.beans.model.YnewsModel;
import com.example.project.beans.param.YnewsParam;

public interface CrawlingYService {
	
	public void crawlingNaverSearchNewsLink(String keyword) throws Exception;
	
	public YnewsModel crawlingNaverNews(YnewsParam newsParam) throws Exception;
}
