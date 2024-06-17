package com.example.project.dao.master;

import java.sql.SQLException;
import java.util.Map;

public interface MsummaryDao {

	public int insertSummary(Map<String, Object> map) throws SQLException;
	
	public int updateSummary(Map<String, Object> map) throws SQLException;
	
	public int deleteSummary(Map<String, Object> map) throws SQLException;
	
}
