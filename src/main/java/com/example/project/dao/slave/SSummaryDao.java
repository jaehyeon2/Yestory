package com.example.project.dao.slave;

import java.util.List;
import java.util.Map;

import com.example.project.beans.model.YSummaryModel;

public interface SSummaryDao {

	public List<YSummaryModel> selectSummaryList(Map<String, Object> mapm) throws Exception;
	
	public YSummaryModel selectSummary(Map<String, Object> map) throws Exception;
	
}
