package com.example.project.dao.slave;

import java.util.List;
import java.util.Map;

import com.example.project.beans.model.YTrendModel;

public interface STrendDao {

	public List<YTrendModel> selectTrendList(Map<String, Object> map) throws Exception;
	
	public YTrendModel selectTrend(Map<String, Object> map) throws Exception;
}
