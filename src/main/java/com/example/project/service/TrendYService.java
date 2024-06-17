package com.example.project.service;

import java.util.List;

public interface TrendYService {
	
	public List<String> getGoogleSearchTrendList() throws Exception;
	
	public boolean insertTrendList(List<String> trendList) throws Exception;
	
}
