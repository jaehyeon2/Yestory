package com.example.project.dao.master;

import java.util.List;
import java.util.Map;

import com.example.project.beans.model.YtrendModel;

public interface MtrendDao {
	
	public int insertTrend(Map<String, Object> map) throws Exception;
	
	public int updateTrend(Map<String, Object> map) throws Exception;
	
	public int deleteTrend(Map<String, Object> map) throws Exception;
	
	public List<YtrendModel> selectTrendList(Map<String, Object> map) throws Exception;
}
