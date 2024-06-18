package com.example.project.dao.master;

import java.util.List;
import java.util.Map;

import com.example.project.beans.model.YTrendModel;

public interface MTrendDao {
	
	public int insertTrend(Map<String, Object> map) throws Exception;
	
	public int updateTrend(Map<String, Object> map) throws Exception;
	
	public int deleteTrend(Map<String, Object> map) throws Exception;
	
	public List<YTrendModel> selectTrendList(Map<String, Object> map) throws Exception;
}
