package com.example.project.dao.master;

import java.util.Map;

public interface MsummaryDao {

	public int insertSummary(Map<String, Object> map) throws Exception;
	
	public int updateSummary(Map<String, Object> map) throws Exception;
	
	public int deleteSummary(Map<String, Object> map) throws Exception;
	
}
