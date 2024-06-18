package com.example.project.dao.slave;

import java.util.List;
import java.util.Map;

import com.example.project.beans.model.YsummaryModel;

public interface SsummaryDao {

	public List<YsummaryModel> selectSummaryList(Map<String, Object> mapm) throws Exception;
	
	public YsummaryModel selectSummary(Map<String, Object> map) throws Exception;
	
}
