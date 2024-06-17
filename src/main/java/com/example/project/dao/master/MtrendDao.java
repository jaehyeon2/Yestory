package com.example.project.dao.master;

import java.sql.SQLException;
import java.util.Map;

public interface MtrendDao {
	
	public int insertTrend(Map<String, Object> map) throws SQLException;
	
	public int updateTrend(Map<String, Object> map) throws SQLException;
	
	public int deleteTrend(Map<String, Object> map) throws SQLException;
}
