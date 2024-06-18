package com.example.project.service;

import java.util.List;

import com.example.project.beans.model.YTrendModel;
import com.example.project.beans.param.TrendParam;

public interface TrendYService {
	
	public boolean saveGoogleSearchTrendList() throws Exception;
	
	public boolean insertTrendList(List<String> trendList) throws Exception;
	
	public List<YTrendModel> selectTrendList(TrendParam trendParam) throws Exception;
	
	public boolean deleteTrend(TrendParam trendParam) throws Exception;
	
}
