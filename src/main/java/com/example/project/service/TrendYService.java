package com.example.project.service;

import java.util.List;

import com.example.project.beans.model.YtrendModel;
import com.example.project.beans.param.YtrendParam;

public interface TrendYService {
	
	public boolean saveGoogleSearchTrendList() throws Exception;
	
	public boolean insertTrendList(List<String> trendList) throws Exception;
	
	public List<YtrendModel> selectTrendList(YtrendParam trendParam) throws Exception;
	
	public boolean deleteTrend(YtrendParam trendParam) throws Exception;
	
}
