package com.example.project.dao.slave;

import java.util.List;
import java.util.Map;

import com.example.project.beans.model.YtrendModel;

public interface StrendDao {

	public List<YtrendModel> selectTrendList(Map<String, Object> map) throws Exception;
	
	public YtrendModel selectTrend(Map<String, Object> map) throws Exception;
}
